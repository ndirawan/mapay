package com.enigma.mapay.service.impl;

import com.enigma.mapay.entity.Upload;
import com.enigma.mapay.repository.UploadRepository;
import com.enigma.mapay.service.UploadService;
import com.enigma.mapay.utils.constant.MessageConstant;
import com.enigma.mapay.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class UploadServiceImpl implements UploadService {

    UploadRepository uploadRepository;
    private static final String UPLOAD_DIR = "./src/main/resources/profile";

    @Override
    public Upload createUpload(Upload upload, MultipartFile photo) throws IOException {

        if (photo != null && !photo.isEmpty()) {
            // Check file type
            String fileType = photo.getContentType();
            if (!fileType.equals("image/png") && !fileType.equals("image/jpeg")) {
                throw new DataNotFoundException("file type --");
            }
            // Generate random characters
            String randomChars = UUID.randomUUID().toString().substring(0, 12);
            String fileName =  randomChars;
            // Get extension of uploaded file
            String fileExtension = FilenameUtils.getExtension(photo.getOriginalFilename());
            // Add extension to file name
            fileName += "." + fileExtension;
            // Get upload path
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(photo.getInputStream(), filePath);
            upload.setUploadName(fileName);
        }

        return uploadRepository.save(upload);
    }

    @Override
    public Upload updateUpload(Upload upload, MultipartFile photo)throws Exception {
        if(uploadRepository.findById(upload.getId()).isPresent()){
            return createUpload(upload,photo);
        }else{
            throw new DataNotFoundException(MessageConstant.MESSAGE_UPDATE);
        }
    }

    @Override
    public Upload getUploadId(String id) {
        return uploadRepository.findById(id).get();
    }

    @Override
    public List<Upload> getAll() {
         return uploadRepository.findAll();
    }
}
