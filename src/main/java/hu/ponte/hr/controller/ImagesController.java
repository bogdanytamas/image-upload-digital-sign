package hu.ponte.hr.controller;


import hu.ponte.hr.entity.ImageMetaEntity;
import hu.ponte.hr.services.ImageStoreService;
import org.h2.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImagesController {

    @Value("${image.folder.path}")
    private String imageFolderPath;

    private final ImageStoreService imageStoreService;

    @Autowired
    public ImagesController(ImageStoreService imageStoreService) {
        this.imageStoreService = imageStoreService;
    }

    @GetMapping("/meta")
    public List<ImageMeta> listImages() {
        // TODO - log
        List<ImageMeta> allImages = imageStoreService.getAllImages();
        // TODO - log
        return allImages;
    }

    // TODO -
    @GetMapping("/preview/{id}")
    public void getImage(@PathVariable("id") Long id, HttpServletResponse response) {
        // TODO - log
        // TODO - Move logic to service layer
        ImageMetaEntity imageMeta = imageStoreService.getImageById(id);
        // TODO - Remove trailing slash in yaml
        File file = new File(imageFolderPath, imageMeta.getName());
        try (InputStream is = new FileInputStream(file)) {
            IOUtils.copy(is, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO - log
    }

}
