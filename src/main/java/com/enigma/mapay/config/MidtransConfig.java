package com.enigma.mapay.config;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransCoreApi;
import com.midtrans.service.MidtransSnapApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MidtransConfig{
    @Value("${midtrans.serverKey}")
    private String serverKey;
    @Value("${midtrans.clientKey}")
    private String clientKey;

    @Bean
    public Config config(){
        return new Config(serverKey, clientKey, false);
    }
    @Bean
    public MidtransSnapApi snapApi(){
        return new ConfigFactory(config()).getSnapApi();
    }

    public MidtransCoreApi coreApi(){
        return new ConfigFactory(config()).getCoreApi();
    }

}
