package info.cheremisin.social.network.initializer;

import info.cheremisin.social.network.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Dmitrii on 06.10.2019.
 */
@Component
@RequiredArgsConstructor
public class ImageInitializer implements ApplicationListener<ContextRefreshedEvent> {

    private final ImageService imageService;

    @Value("classpath:profileImages/*")
    private Resource[] resources;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            Path pathImages = imageService.getProfileImagesPath();
            if(pathImages.toFile().exists()) {
                Files.walk(pathImages)
                        .map(Path::toFile)
                        .forEach(File::delete);
                Files.delete(pathImages);
            }
            Files.createDirectory(pathImages);

            for (Resource resource : resources) {
                InputStream inputStream = resource.getInputStream();
                Files.copy(inputStream, pathImages.resolve(resource.getFilename()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
