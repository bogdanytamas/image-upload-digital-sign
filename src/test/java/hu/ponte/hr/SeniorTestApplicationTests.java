package hu.ponte.hr;

import hu.ponte.hr.controller.ImagesController;
import hu.ponte.hr.controller.upload.UploadController;
import hu.ponte.hr.repository.ImageMetaRepository;
import hu.ponte.hr.services.ImageStoreService;
import hu.ponte.hr.services.SignService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeniorTestApplicationTests
{

	@Autowired
	private ImagesController imagesController;

	@Autowired
	private UploadController uploadController;

	@Autowired
	private ImageMetaRepository imageMetaRepository;

	@Autowired
	private ImageStoreService imageStoreService;

	@Autowired
	private SignService signService;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(imagesController);
		Assertions.assertNotNull(uploadController);
		Assertions.assertNotNull(imageMetaRepository);
		Assertions.assertNotNull(imageStoreService);
		Assertions.assertNotNull(signService);
	}

}

