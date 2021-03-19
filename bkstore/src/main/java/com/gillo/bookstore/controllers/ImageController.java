package com.gillo.bookstore.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gillo.bookstore.model.Book;
import com.gillo.bookstore.services.BookService;
import com.gillo.bookstore.services.ImageService;

/**
 * @author Gilbert Lopez
 *
 * Created on Jan 29, 2021
 */

@Controller
public class ImageController {
	
    private final ImageService imageService;
    private final BookService bookService;
	
    public ImageController(ImageService imageService, BookService bookService) {
		this.imageService = imageService;
		this.bookService = bookService;
	}

	@GetMapping("book/{id}/image")
    public String imageUploadForm(@PathVariable Long id, Model model){
		
        model.addAttribute("book", bookService.getBookById(id));
        return "imageupload";
    }

    @PostMapping("book/{id}/image")
    public String saveImage(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){
    	
        imageService.saveImageFile(id, file);
        return "redirect:/admin/editBook/" + id;
    }

    @GetMapping("book/{id}/showimage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
    	
    	Book book = bookService.getBookById(id);

        if (book.getImage() != null) {
            byte[] byteArray = new byte[book.getImage().length];
            int i = 0;

            for (Byte wrappedByte : book.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            is.transferTo(response.getOutputStream());
        }
    }
}
