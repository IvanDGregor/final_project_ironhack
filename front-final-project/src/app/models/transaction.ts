import { InformationContact } from './informationContact';
import { TransactionType } from './transactionType';
import { Identifiers } from '@angular/compiler';

export interface Transaction {
    id: string;
    accountSenderId: string;
    accountReceiverId: string;
    amount: number;
    date: string;
    typeTransaction: TransactionType;
    userId: string;
}
