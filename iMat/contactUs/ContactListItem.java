package iMat.contactUs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ContactListItem extends AnchorPane {
    private ContactController parentController;
    private Contact contact;
    @FXML
    private ImageView profilePic;
    @FXML
    private Label nameLabel;
    @FXML
    private Label emailLabel;
    @FXML
    private Label phoneLabel;

    public ContactListItem(Contact contact, ContactController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contact_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.contact = contact;
        profilePic.setImage(contact.getFXImage());
        nameLabel.setText(contact.getName());
        emailLabel.setText(contact.getEmail());
        phoneLabel.setText(contact.getPhoneNumber());
    }
}
