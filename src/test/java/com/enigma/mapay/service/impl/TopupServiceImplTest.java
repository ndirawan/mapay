package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.Topup;
import com.enigma.mapay.entity.TopupDetail;
import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.TopupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TopupServiceImplTest {

    private Topup topup;
    @BeforeEach
    void setup() {
        topup = new Topup("01", LocalDateTime.now(),new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0), new TopupDetail());
    }

    @AfterEach
    void cleanUp(){
        topup = new Topup();
    }

    @Mock
    TopupRepository topupRepository;

    @InjectMocks
    TopupServiceImpl topupService;
    @Test
    void saveTopup() {
    }

    @Test
    void getAllTopUp() {
        List<Topup> listOfTopup = new ArrayList<>();
        Topup topup = new Topup("01", LocalDateTime.now(),new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0), new TopupDetail());
        Topup topup1 = new Topup("02", LocalDateTime.now(),new User("01", "tika@gmail.com", "082165417356", "tika", "jakarta", Date.valueOf("1999-10-10"),null,1,0,0), new TopupDetail());
        listOfTopup.add(topup);
        listOfTopup.add(topup1);

        when(topupRepository.findAll()).thenReturn(listOfTopup);

        List<Topup> topupList = topupService.getAllTopUp();
        assertEquals(2, topupList.size());

        verify(topupRepository, times(1)).findAll();
    }

    @Test
    void getTopupById() {
        when(topupRepository.findById("01")).thenReturn(Optional.of(Optional.of(topup).get()));
        Topup topup = topupService.getTopupById("01");
        assertEquals("tika", topup.getUser().getFullName());
        verify(topupRepository, times(1)).findById("01");
    }
}