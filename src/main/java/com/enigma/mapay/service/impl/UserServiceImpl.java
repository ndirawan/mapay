package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.User;
import com.enigma.mapay.repository.UserRepository;
import com.enigma.mapay.service.UserService;
import com.enigma.mapay.utils.constant.MessageConstant;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        if (userRepository.findById(user.getId()).isPresent()){
            return saveUser(user);
        }else {
            throw new DataNotFoundException(MessageConstant.MESSAGE_UPDATE+ user.getId());
        }
    }
    @Override
    public User getUserById(String id) {
        try {
            if (userRepository.findById(id).isPresent()){
                return userRepository.findById(id).get();
            }else throw new DataNotFoundException("DATA NOT FOUND");
        }catch (DataNotFoundException e){
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserByPhoneNumb(String phone) {
        return userRepository.findByPhoneNumb(phone);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
