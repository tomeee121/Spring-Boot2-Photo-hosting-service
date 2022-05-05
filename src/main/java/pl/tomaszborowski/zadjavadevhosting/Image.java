package pl.tomaszborowski.zadjavadevhosting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String adressImage;

    public Image(String adressImage) {
        this.adressImage = adressImage;
    }

    public Image(Long id, String adressImage) {
        this.id = id;
        this.adressImage = adressImage;
    }

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdressImage() {
        return adressImage;
    }

    public void setAdressImage(String adressImage) {
        this.adressImage = adressImage;
    }
}
