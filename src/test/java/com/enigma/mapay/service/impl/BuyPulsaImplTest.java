package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.BuyPulsa;
import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.BuyPulsaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BuyPulsaImplTest {
    private BuyPulsa buyPulsa;
    @BeforeEach
    void setup() {
        buyPulsa = new BuyPulsa("01", LocalDateTime.now(), new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0), new BuyPulsaDetail());
    }

    @AfterEach
    void cleanUp(){
        buyPulsa = new BuyPulsa();
    }

    @Mock
    BuyPulsaRepository buyPulsaRepository;

    @InjectMocks
    BuyPulsaImpl buyPulsaService;

    @Test
    void savePulsa() {
    }

    @Test
    void findId() {
        when(buyPulsaRepository.findById("01")).thenReturn(Optional.of(Optional.of(buyPulsa).get()));
        BuyPulsa buyPulsa1 = buyPulsaService.findId("01");
        assertEquals("tika", buyPulsa1.getUser().getFullName());
        verify(buyPulsaRepository, times(2)).findById("01");
    }
}