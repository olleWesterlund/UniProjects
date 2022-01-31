package iMat.searchList;

import iMat.CardListener;
import iMat.IMatController;
import iMat.checkout.CardStyle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.text.DecimalFormat;

public class SearchListNoMatch extends AnchorPane implements CardListener {
    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private IMatController parentController;

    public SearchListNoMatch(IMatController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search_list_no_match.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
    }

    @Override
    public void actOnChange() {

    }
}
