package com.enigma.mapay.controller;

import com.enigma.mapay.entity.Upload;
import com.enigma.mapay.service.UploadService;
import com.enigma.mapay.utils.constant.ApiUrlConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.UPLOAD_PATH)
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

    @PutMapping("/{id}")
    public Upload update(@PathVariable String id,@ModelAttribute Upload upload,
                         @RequestParam(name = "file", required = false) MultipartFile photo) throws Exception {
        upload.setId(id);
        Upload update = uploadService.updateUpload(upload,photo);
        return update;
    }
}
