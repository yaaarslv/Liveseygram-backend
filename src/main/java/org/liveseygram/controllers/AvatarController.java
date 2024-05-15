package org.liveseygram.controllers;

import org.liveseygram.services.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
public class AvatarController {
    private final AvatarService avatarService;

    @Autowired
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping("/avatar/{userIdAndFileExtension}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String userIdAndFileExtension) {
        String filePath = "C:\\Users\\Ляляха\\IdeaProjects\\Liveseygram\\src\\main\\images\\" + userIdAndFileExtension;

        try {
            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        try {
            avatarService.saveAvatar(file, userId);
            return ResponseEntity.ok("Файл успешно загружен.");
        } catch (IOException e) {
            return ResponseEntity.ok("Ошибка при загрузке файла: " + e.getMessage());
        }
    }
}
