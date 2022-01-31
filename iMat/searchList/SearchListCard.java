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

public class SearchListCard extends AnchorPane implements CardListener {
    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private Product product;
    private IMatController parentController;
    @FXML
    private ImageView productImage;
    @FXML
    private Label productLabel;
    @FXML
    private ImageView flagImage;
    @FXML
    private Label priceLabel;
    @FXML
    private AnchorPane itemAnchorPane;

    public SearchListCard(IMatController parentController, Product product, CardStyle cardStyle, Boolean light) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search_list_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.product = product;

        DecimalFormat df = new DecimalFormat("#.#");
        double price = product.getPrice();
        productImage.setImage(dataHandler.getFXImage(product));
        productLabel.setText(product.getName());
        priceLabel.setText(df.format(price) + " " + product.getUnit());
//        if (light) {itemAnchorPane.getStyleClass().add("pyjamas-list-light");}
//        else       {itemAnchorPane.getStyleClass().add("pyjamas-list-dark");}
    }

    @FXML
    protected void onClick(Event event) {
        // TODO: Ersätt new ShoppingItem, med ett shoppingItem som hör till kundvagnen (eller något sådant)
        parentController.openDetailedView(new ShoppingItem(product, 0), this);
    }

    @Override
    public void actOnChange() {

    }
}
