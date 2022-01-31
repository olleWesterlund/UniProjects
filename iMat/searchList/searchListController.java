package iMat.searchList;

import iMat.IMatController;
import iMat.Model;
import iMat.checkout.CardStyle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.HashMap;

public class searchListController extends AnchorPane {
    private IMatController parentController;
    private Model model;
    private SearchItemList searchItemList;

    @FXML
    private FlowPane productFlowPane;
    @FXML
    private FlowPane categoryFlowPane;
    @FXML
    private ListView<String> searchListView;
    @FXML
    private FlowPane messageNoMatch;
    @FXML
    private Hyperlink linkShowAllMatchingProducts;

    public searchListController(IMatController parentController, String searchText){
        this.parentController = parentController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("search_List.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Search for items and categories in the database
        searchItemList = new SearchItemList(searchText);

        Boolean light = false;
        if (!searchItemList.hasProducts()) {
            // No products matches the search criteria. Show a message about that.
            productFlowPane.getChildren().add(new SearchListNoMatch(parentController));
            light = !light;
        } else {
            for (int i = 0; i < Math.min(5, searchItemList.numProducts()); i++) {
                productFlowPane.getChildren().add(new SearchListCard(parentController, searchItemList.getProduct(i), CardStyle.Low_Wide, light));
                light = !light;
            }
        }
        if (searchItemList.numProducts() > 5){
            // Alla träffar kan inte visas i träfflistan. Skapa en länk med
            // träfflistran som användaren kan klicka på för att se alla träffar.
            linkShowAllMatchingProducts.setVisible(true);
        } else {
            linkShowAllMatchingProducts.setVisible(false);
        }


        HashMap<String, ProductCategory> category = searchItemList.getCategoryMap();
        for (String categoryName : category.keySet()) {
            categoryFlowPane.getChildren().add(new SearchListCategory(parentController, categoryName, category.get(categoryName), light));
            light = !light;
        }
    }

    @FXML
    protected void onClick(Event event) {
        parentController.displayProductList(searchItemList.getProducts());
    }

}
