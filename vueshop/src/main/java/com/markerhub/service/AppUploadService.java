package com.markerhub.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AppUploadService {

	List<String> upload(MultipartFile[] pics, float scale, double quality) throws IOException;

}
