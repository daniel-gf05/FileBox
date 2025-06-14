package com.daniel.filebox.Controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.daniel.filebox.Models.File;
import com.daniel.filebox.Repositories.FileRepository;

@RestController
@RequestMapping("/filebox")
public class FileController {

    private final FileRepository fileRepository;

    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * Adds a file in database
     */
    @PostMapping("/add")
    public void addFile(@RequestParam("description") String description,
            @RequestParam("file") MultipartFile multipartFile) {
        try {
            File file = new File();
            file.setFileName(multipartFile.getOriginalFilename());
            file.setFileDescription(description);
            file.setFileSize(multipartFile.getSize());
            file.setFile(multipartFile.getBytes());

            fileRepository.save(file);
            System.out.println("File saved succesfully");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Deletes a file from database
     */
    @DeleteMapping("/delete")
    public void deleteFile(@RequestParam("id") Long id) {
        try {
            fileRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    /**
     * Lists all files
     */
    @GetMapping("/list")
    public void listFiles() {
        try {
            if (fileRepository.count() == 0) {
                System.err.println("Empty database");
                return;
            }
            fileRepository.findAll();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }
}
