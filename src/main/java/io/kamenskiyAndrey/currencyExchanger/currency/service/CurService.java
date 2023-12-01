package io.kamenskiyAndrey.currencyExchanger.currency.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.kamenskiyAndrey.currencyExchanger.currency.client.CurrencyDateHttpClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class CurService {
    private CurrencyDateHttpClient client;
    private final Cache<LocalDate, Map<String, BigDecimal>> cache; //поле для кеша

    public CurService(CurrencyDateHttpClient client) {
        this.cache = CacheBuilder.newBuilder().build();
        this.client = client;
    }

    public BigDecimal requestByCurrencyCode(String currencyCode) {

        try {
            return cache.get(LocalDate.now(), this::callAllByCurrentDate).get(currencyCode);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
    //TODO
    private Map<String, BigDecimal> callAllByCurrentDate() {
        var xml = client.requestByDate(LocalDate.now()); // получим xml - в нем будут котировки всех валют за текщую дату
        return null;
    }

}
