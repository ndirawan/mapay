package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.BuyPulsaDetail;
import com.enigma.mapay.repository.BuyPulsaDetailRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class BuyPulsaDetailServiceImplTest {
    private BuyPulsaDetail buyPulsaDetail;
    @BeforeEach
    void setup() {
        buyPulsaDetail = new BuyPulsaDetail("01", "tipe", "1234", "code", 10000, "done");
    }

    @AfterEach
    void cleanUp(){
        buyPulsaDetail = new BuyPulsaDetail();
    }

    @Mock
    BuyPulsaDetailRepository buyPulsaDetailRepository;

    @InjectMocks
    BuyPulsaDetailServiceImpl buyPulsaDetailService;

    @Test
    void save() {
        when(buyPulsaDetailRepository.save(buyPulsaDetail)).thenReturn(buyPulsaDetail);
        BuyPulsaDetail savedBuyPulsaDetail = buyPulsaDetailService.save(buyPulsaDetail);
        assertNotNull(savedBuyPulsaDetail);
        assertEquals(buyPulsaDetail.getId(),savedBuyPulsaDetail.getId());
        assertEquals(buyPulsaDetail.getPulsaCode(),savedBuyPulsaDetail.getPulsaCode());

        verify(buyPulsaDetailRepository,times(1)).save(buyPulsaDetail);
    }

    @Test
    void getById() {
        when(buyPulsaDetailRepository.findById("01")).thenReturn(Optional.of(Optional.of(buyPulsaDetail).get()));
        BuyPulsaDetail buyPulsaDetail1 = buyPulsaDetailService.getById("01");
        assertEquals("tipe", buyPulsaDetail1.getType());
        verify(buyPulsaDetailRepository, times(2)).findById("01");
    }
}