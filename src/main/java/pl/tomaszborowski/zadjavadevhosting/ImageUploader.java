package pl.tomaszborowski.zadjavadevhosting;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import pl.tomaszborowski.zadjavadevhosting.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Component
public class ImageUploader {

        private ImageRepo imageRepo;
        private Cloudinary cloudinary;

        public ImageUploader(ImageRepo imageRepo){
            this.imageRepo = imageRepo;
            cloudinary = new Cloudinary(ObjectUtils.asMap(
                    "cloud_name", "dxnnxpn0b",
                    "api_key", "567555153516168",
                    "api_secret", "sEF0AewkEp82tfs1rRYzuw-9jLg",
                    "secure", true));
        }

        public String uploadFile(String path) {
            File file = new File(path);
            Map uploadResult;
            {
                try {
                    uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                    imageRepo.save(new Image(uploadResult.get("url").toString()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return "";
        }
}

