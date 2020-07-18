package com.ironhack.edgeservice.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static feign.FeignException.errorStatus;


@Component
public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response)  {
        if (response.status() >= 400 && response.status() <= 499) {
            return new ResponseStatusException(HttpStatus.valueOf(response.status()), "Error caused by " + methodKey);
        }
        return errorStatus(methodKey, response);
    }
}
