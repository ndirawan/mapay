package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.entity.TopupDetail;
import com.enigma.mapay.repository.TopupDetailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TopupDetailServiceImplTest {
    @Mock
    TopupDetailRepository topupDetailRepository;

    @InjectMocks
    TopupDetailServiceImpl topupDetailService;

    @Test
    void saveTopupDetail() {
        TopupDetail topupDetail = new TopupDetail("01", 100000000, "transfermn", "berhasil");
        when(topupDetailRepository.save(topupDetail)).thenReturn(topupDetail);
        TopupDetail savedTopupDetail = topupDetailService.saveTopupDetail(topupDetail);
        assertNotNull(savedTopupDetail);
        assertEquals(topupDetail.getId(),savedTopupDetail.getId());
        assertEquals(topupDetail.getMethode(),savedTopupDetail.getMethode());

        verify(topupDetailRepository,times(1)).save(topupDetail);
    }
}