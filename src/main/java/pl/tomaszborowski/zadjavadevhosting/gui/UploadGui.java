package pl.tomaszborowski.zadjavadevhosting.gui;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.tomaszborowski.zadjavadevhosting.ImageUploader;

import java.awt.event.ActionListener;


@Route("uploadImage")
public class UploadGui extends VerticalLayout {
//    public UploadGui(){
//        Button button = new Button();
//        TextField textField = new TextField();
//        add(textField);
//        add(button);
//    }

    private ImageUploader imageUploader;

//    @Autowired
//    public UploadGui(ImageUploader imageUploader) {
//        this.imageUploader = imageUploader;
//    }

    @Autowired
    public UploadGui(ImageUploader imageUploader) {
        this.imageUploader = imageUploader;

        TextField textField = new TextField();
        Button button = new Button("upload");
        button.addClickListener(event -> {imageUploader.uploadFile(textField.getValue());

//        String s = imageUploader.uploadFile(textField.getValue());
//        Image image = new Image(s, "nie udało się dodać");
//            add(image);
        });



        add(textField);
        add(button);
    }

}
