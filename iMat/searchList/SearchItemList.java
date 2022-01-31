package iMat.searchList;

import iMat.Model;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchItemList {
    List<Product> products = new ArrayList<>();
    HashMap<String, ProductCategory> categoryMap = new HashMap<>();
    /**
     * Search for items in the database
     * @param searchText the name or a part of the name of an item
     */
    public SearchItemList(String searchText){
        System.out.println(String.format("Start db-search: %s", searchText)); // TODO: Delete this row
        Model db = Model.getInstance();

        // Search for items
        if (!searchText.isEmpty()) {
            SearchProduct searchProduct = new SearchProduct();
            products = searchProduct.find(searchText);
        }

//        for (Product product: products){
//            categoryMap.put(db.getCategory(product.getCategory().name()));
//        }
        // Search for categories
        categoryMap = db.findCategories(searchText);
        if (products.isEmpty() && categoryMap.isEmpty()){
            // If we didn't find any products or categories that match the search text
            // get all categories as a search result.
            categoryMap = db.findCategories("");
        }
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCategoryMap(HashMap<String, ProductCategory> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public List<Product> getProducts() {
        return products;
    }

    public HashMap<String, ProductCategory> getCategoryMap() {
        return categoryMap;
    }

    public boolean hasProducts (){
        return !products.isEmpty();
    }
    public int numProducts(){
        return products.size();
    }
    public Product getProduct(int i){
        return products.get(i);
    }
}
