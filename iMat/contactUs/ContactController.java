package iMat.contactUs;

import iMat.BackendController;
import iMat.IMatController;
import iMat.shoppingCart.FocusWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContactController extends AnchorPane implements FocusWindow {

    private IMatController parentController;
    private BackendController backendController = new BackendController();
    private Map<String, ContactListItem> contactListItemMap = new HashMap<String, ContactListItem>();

    @FXML private FlowPane contactsFlowPane;

    public ContactController(IMatController parentController) {
        this.parentController = parentController;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("contactUs.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        for (Contact contact : backendController.getContacts()) {
            ContactListItem contactListItem = new ContactListItem(contact, this);
            contactListItemMap.put(contact.getName(), contactListItem);
        }
        for (Contact contact : backendController.getContacts()) {
            contactsFlowPane.getChildren().add(contactListItemMap.get(contact.getName()));
        }
    }

    @Override
    public void update() {

    }

    @Override
    public String getTitle() {
        return "Kontakta Oss";
    }
}
