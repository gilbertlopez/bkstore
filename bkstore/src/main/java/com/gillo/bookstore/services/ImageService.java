package com.gillo.bookstore.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 29, 2021
 */
public interface ImageService {

	void saveImageFile(Long bookId, MultipartFile file);
}
