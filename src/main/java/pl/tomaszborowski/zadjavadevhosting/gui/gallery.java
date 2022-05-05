package pl.tomaszborowski.zadjavadevhosting.gui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.tomaszborowski.zadjavadevhosting.Image;
import pl.tomaszborowski.zadjavadevhosting.repo.ImageRepo;

import java.util.List;

@Route("gallery")
public class gallery extends VerticalLayout {
    private ImageRepo imageRepo;

    @Autowired
    public gallery(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;

        List<Image> all = imageRepo.findAll();
        all.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image = new com.vaadin.flow.component.html.Image(element.getAdressImage(), "nie renderuje :/");
            add(image);
        });

    }
}
