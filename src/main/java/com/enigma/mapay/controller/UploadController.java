package com.enigma.mapay.controller;

import com.enigma.mapay.entity.Upload;
import com.enigma.mapay.service.UploadService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/upload")
@AllArgsConstructor
public class UploadController {
   private UploadService uploadService;
    @PostMapping
    public ResponseEntity<Upload> createUpload(@ModelAttribute Upload upload,
                                               @RequestParam(name = "file", required = false) MultipartFile photo)
            throws Exception {
        Upload createdUpload = uploadService.createUpload(upload, photo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUpload);
    }
    @GetMapping
    public List<Upload> getUpload(){
        return uploadService.getAll();
    }
}
