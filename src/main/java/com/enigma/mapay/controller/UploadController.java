package com.enigma.mapay.controller;

import com.enigma.mapay.entity.Upload;
import com.enigma.mapay.repository.UploadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
     UploadsRepository uploadsRepository;

    @Autowired
     PasswordEncoder passwordEncoder;

    private static final String UPLOAD_DIR = "./uploads/";


    @PostMapping
    public ResponseEntity<Upload> createUser(@RequestParam("name") String name,
                                           @RequestParam("email") String email,
                                           @RequestParam("password") String password,
                                           @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {
        Upload upload = new Upload();
        upload.setName(name);
        upload.setEmail(email);
        upload.setPassword(passwordEncoder.encode(password));
        if (photo != null && !photo.isEmpty()) {
            String fileName = StringUtils.cleanPath(photo.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(photo.getInputStream(), filePath);
            upload.setPhoto(fileName);
        }
        uploadsRepository.save(upload);
        return ResponseEntity.ok(upload);
    }
    @GetMapping
    public List<Upload> getAllUser(){
        return uploadsRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Upload> getUserById(@PathVariable("id") Long id) {
        Optional<Upload> optionalUser = uploadsRepository.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
