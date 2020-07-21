package com.ironhack.operationsservice.service;

import com.ironhack.operationsservice.exception.AccountNotFoundException;
import com.ironhack.operationsservice.exception.DataNotFoundException;
import com.ironhack.operationsservice.exception.FraudDetectionException;
import com.ironhack.operationsservice.model.classes.Account;
import com.ironhack.operationsservice.model.classes.CreditCard;
import com.ironhack.operationsservice.model.classes.Transaction;
import com.ironhack.operationsservice.model.dto.PaymentDTO;
import com.ironhack.operationsservice.model.dto.TransferDTO;
import com.ironhack.operationsservice.model.enums.Status;
import com.ironhack.operationsservice.model.enums.TypeTransaction;
import com.ironhack.operationsservice.repository.AccountRepository;
import com.ironhack.operationsservice.repository.CreditCardRepository;
import com.ironhack.operationsservice.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Transactional
    public Transaction transfer(TransferDTO transferDTO){
        Account accountSender = accountRepository.findById(transferDTO.getAccountSenderId()).orElseThrow(() -> new AccountNotFoundException("There's no account with provided ID"));

        if (accountSender.getStatus() == Status.FROZEN) throw new FraudDetectionException("Your account is blocked for fraud inspection purposes, please contact customer service");
        Account accountReceiver = accountRepository.findById(transferDTO.getAccountReceiverId()).orElseThrow(() -> new AccountNotFoundException("There's no reciever account with provided ID"));
        if (accountReceiver.getStatus() == Status.FROZEN) throw new FraudDetectionException("The account receiver is blocked");
        if(transferDTO.getSecret_key().equals(accountSender.getSecret_key())){
            if(transferDTO.getAmount().compareTo(accountSender.getBalance()) <= 0 ){
                accountSender.setBalance(accountSender.getBalance().subtract(transferDTO.getAmount()));
                accountReceiver.setBalance(accountReceiver.getBalance().add(transferDTO.getAmount()));
                accountRepository.save(accountSender);
                accountRepository.save(accountReceiver);
                Transaction transaction = new Transaction(transferDTO.getAccountSenderId(),transferDTO.getAccountReceiverId(),accountSender.getUserId(),transferDTO.getAmount(), LocalDateTime.now(), TypeTransaction.TRANSFER);
                transactionRepository.save(transaction);
                return transaction;
            }
            throw new FraudDetectionException("Not enough balance in the account");
        }
        throw new FraudDetectionException("Invalid Secret Key");
    }
    @Transactional
    public Transaction payment(PaymentDTO paymentDTO){
        CreditCard creditCard = creditCardRepository.findById(paymentDTO.getCreditCardId()).orElseThrow(() -> new DataNotFoundException("There's no creditcard with provided ID"));
        if(creditCard.getStatus() == Status.FROZEN) throw new FraudDetectionException("This credit card is blocked");
        if(!paymentDTO.getPin().equals(creditCard.getPin())) throw new FraudDetectionException("The PIN is invalid");
        Account account = accountRepository.findById(creditCard.getAccountId()).orElseThrow(() -> new DataNotFoundException("There's no account with this ID"));
        if(account.getStatus() == Status.FROZEN) throw new FraudDetectionException("This account is blocked");
        if(paymentDTO.getAmount().compareTo(account.getBalance()) <= 0){
            account.setBalance(account.getBalance().subtract(paymentDTO.getAmount()));
            accountRepository.save(account);
            Transaction transaction = new Transaction(account.getId(),creditCard.getId(),creditCard.getUserId(),paymentDTO.getAmount(), LocalDateTime.now(), TypeTransaction.CREDITCARD);
            transactionRepository.save(transaction);
            return transaction;
        }
        throw new FraudDetectionException("Not enough balance in the account");
    }
}
