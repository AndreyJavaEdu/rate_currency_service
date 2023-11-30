package io.kamenskiyAndrey.currencyExchanger.currency.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class CurService {

    private final Cache<LocalDate, Map<String, BigDecimal>> cache; //поле для кеша

    public CurService() {
        this.cache = CacheBuilder.newBuilder().build();
    }


    public BigDecimal requestByCurrencyCall(String currencyСode){

        try {
            return cache.get(LocalDate.now(), this::callAllByCurrentDate).get(code);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {
    }
}
