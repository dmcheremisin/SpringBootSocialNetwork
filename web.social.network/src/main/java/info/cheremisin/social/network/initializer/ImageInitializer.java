package info.cheremisin.social.network.initializer;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Dmitrii on 06.10.2019.
 */
@Component
@Profile({"dev", "qa"})
public class ImageInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println(">>>> Location: ");
        Path path = Paths.get(".");
        System.out.println(path.toAbsolutePath());
        Resource resource = new ClassPathResource("profileImages/1.jpg");
        try {
            File file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
