package com.gillo.bookstore.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gillo.bookstore.exceptions.BookNotFoundException;
import com.gillo.bookstore.model.Book;
import com.gillo.bookstore.repositories.BookRepository;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 29, 2021
 */

@Service
public class ImageServiceImpl implements ImageService {
	
	private final BookRepository bookRepository;
	

	public ImageServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	@Override
	public void saveImageFile(Long bookId, MultipartFile file) {
		
		Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        
		try {
            Byte[] byteObjects = new Byte[file.getBytes().length];

            int i = 0;

            for (byte b : file.getBytes()){
                byteObjects[i++] = b;
            }
            
            book.setImage(byteObjects);
            bookRepository.save(book);
            
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
}
