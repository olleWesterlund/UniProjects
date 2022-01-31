package iMat.checkout;

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

public class ProductCard extends AnchorPane {
    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private ShoppingItem shoppingItem;
    @FXML
    private ImageView productImage;
    @FXML
    private Label productLabel;
    @FXML
    private ImageView flagImage;
    @FXML
    private Label priceLabel;
    @FXML
    private Label totalPriceLabel;
    @FXML
    private AnchorPane itemAnchorPane;
/*
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label categoryLabel;
*/
    public ProductCard(ShoppingItem item, CardStyle cardStyle, Boolean light) {
        FXMLLoader fxmlLoader = getFxmlFile(cardStyle);
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_card_low_wide.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.shoppingItem = item;

        DecimalFormat df = new DecimalFormat("#.#");
        Product product = item.getProduct();
        double price = product.getPrice();
        productImage.setImage(dataHandler.getFXImage(product));
        productLabel.setText(product.getName());
        priceLabel.setText(df.format(price) + " " + product.getUnit());
        totalPriceLabel.setText(df.format(item.getTotal()) + " kr");
        if (light) {itemAnchorPane.getStyleClass().add("pyjamas-list-light");}
        else       {itemAnchorPane.getStyleClass().add("pyjamas-list-dark");}
    }

    private FXMLLoader getFxmlFile(CardStyle cardStyle){
        FXMLLoader fxmlLoader;
        switch (cardStyle){
            case Low_Wide:
                fxmlLoader = new FXMLLoader(getClass().getResource("product_card_low_wide.fxml"));
                break;
            case High_Wide:
                fxmlLoader = new FXMLLoader(getClass().getResource("product_card_wide.fxml"));
                break;
            case High_Narrow:
                fxmlLoader = new FXMLLoader(getClass().getResource("product_card.fxml"));
                break;
            default:
                throw new IllegalStateException(String.format("The CardStyle, %s, is not correct.", cardStyle));
        }
        return fxmlLoader;
    }

    @FXML
    protected void onClick(Event event) {

    }


}
