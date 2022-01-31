package iMat.contactUs;

import javafx.scene.image.Image;

public class Contact {
    private String name;
    private final String imagePath;
    private String email;
    private String phoneNumber;

    public Contact(String name, String imagePath, String email, String phoneNumber) {
        this.name = name;
        this.imagePath = imagePath;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Image getFXImage() {
        return new Image("file:" + this.imagePath);
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
}
