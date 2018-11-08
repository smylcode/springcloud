package com.example.servicefeign;

import org.springframework.stereotype.Component;

/**
 * @author gouchao
 * @since 2018.7.17 16:57
 */
@Component
public class FeignServiceHystric implements FeignService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
