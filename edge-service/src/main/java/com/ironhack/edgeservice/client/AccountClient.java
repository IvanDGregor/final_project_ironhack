package com.ironhack.edgeservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name ="account-service")
public interface AccountClient {
}
