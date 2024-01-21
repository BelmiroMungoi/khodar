package com.bbm.khodar.service;

import com.bbm.khodar.dto.response.HttpResponse;
import com.bbm.khodar.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    HttpResponse upload(MultipartFile file, Long eventId);
    Image download(Long eventId);
    void delete(Long eventId);
}
