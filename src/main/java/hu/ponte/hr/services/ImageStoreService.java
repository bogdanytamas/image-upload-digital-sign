package hu.ponte.hr.services;

import hu.ponte.hr.controller.ImageMeta;
import hu.ponte.hr.entity.ImageMetaEntity;
import hu.ponte.hr.repository.ImageMetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageStoreService {

    @Value("${image.folder.path}")
    private String imageFolderPath;

    private final SignService signService;
    private final ImageMetaRepository imageMetaRepository;

    @Autowired
    public ImageStoreService(SignService signService, ImageMetaRepository imageMetaRepository) {
        this.signService = signService;
        this.imageMetaRepository = imageMetaRepository;
    }

    public ImageMetaEntity saveImage(MultipartFile file) {
        ImageMetaEntity imageMetaEntity = new ImageMetaEntity();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imageFolderPath + file.getOriginalFilename());
            Files.write(path, bytes);

            String digitalSignature = convertByteArrayToBase64(signService.signImage(file.getBytes()));

            ImageMetaEntity imageMeta = ImageMetaEntity.builder()
                    .name(file.getOriginalFilename())
                    .mimeType(file.getContentType())
                    .size(file.getSize())
                    .digitalSign(digitalSignature)
                    .build();

            imageMetaEntity = imageMetaRepository.save(imageMeta);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageMetaEntity;
    }

    private String convertByteArrayToBase64(byte[] file) {
        return Base64.getEncoder().encodeToString(file);
    }

    public List<ImageMeta> getAllImages() {
        return imageMetaRepository.findAll()
                .stream()
                .map(ImageMeta::new)
                .collect(Collectors.toList());
    }

    public ImageMetaEntity getImageById(Long id) {
        return imageMetaRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nem található a " + id + "-jú kép!"));
    }

}
