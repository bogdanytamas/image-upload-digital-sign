package hu.ponte.hr.controller.upload;

import hu.ponte.hr.services.ImageStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

// TODO - Should be RestController or Controller
@Component
@RequestMapping("api/file")
public class UploadController
{
    private final ImageStoreService imageStoreService;

    @Autowired
    public UploadController(ImageStoreService imageStoreService) {
        this.imageStoreService = imageStoreService;
    }

    @RequestMapping(value = "post", method = RequestMethod.POST)
    @ResponseBody
    public String handleFormUpload(@RequestParam("file") MultipartFile file){
        imageStoreService.saveImage(file);
        return "ok";
    }

}
