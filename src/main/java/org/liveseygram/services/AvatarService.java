package org.liveseygram.services;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

import org.liveseygram.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AvatarService {
    private final UserService userService;

    @Autowired
    public AvatarService(UserService userService) {
        this.userService = userService;
    }

    public void saveAvatar(MultipartFile file, Long userId) throws IOException {
        String uploadDir = "C:\\Users\\Ляляха\\IdeaProjects\\Liveseygram\\src\\main\\images";

        String originalFileName = file.getOriginalFilename();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileName = userId.toString() + fileExtension;
        String filePath = uploadDir + "/" + fileName;
        String avatarUrl = "http://localhost:8080/avatar/" + userId.toString() + fileExtension;


        File dest = new File(filePath);
        file.transferTo(dest);

        Optional<User> user = userService.findById(userId);
        if (user.isPresent()) {
            User user1 = user.get();
            user1.setAvatarPath(avatarUrl);
            userService.save(user1);
        }
    }
}

