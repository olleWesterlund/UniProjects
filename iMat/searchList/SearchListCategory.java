package iMat.searchList;

import iMat.IMatController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.text.DecimalFormat;

public class SearchListCategory extends AnchorPane {
    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    private ProductCategory productCategory;
    private IMatController parentController;

    @FXML
    private Label productLabel;
    @FXML
    private AnchorPane itemAnchorPane;

    public SearchListCategory(IMatController parentController, String categoryName, ProductCategory productCategory, Boolean light) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search_list_category.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
        this.productCategory = productCategory;

        productLabel.setText(categoryName);
//        if (light) {itemAnchorPane.getStyleClass().add("pyjamas-list-light");}
//        else       {itemAnchorPane.getStyleClass().add("pyjamas-list-dark");}
    }

    @FXML
    protected void onClick(Event event) {
        parentController.displayProductCategory(productCategory);
    }
}
