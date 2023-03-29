package com.enigma.mapay.service;

import com.enigma.mapay.entity.Upload;
import com.enigma.mapay.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    Upload createUpload(Upload upload, MultipartFile photo)throws Exception;

    Upload updateUpload(Upload user, MultipartFile photo)throws Exception;

    Upload getUploadId(String id);

    List<Upload> getAll();
}
