package iMat;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.io.File;
import java.lang.reflect.Array;
import java.util.*;

public class Model {
    private final IMatDataHandler parent;
    private Map<String,ShoppingItem> shoppingItemMap;
    private static Model instance;

    private List<ShoppingItem> previousShoppingCart;

    private Model() {
        parent = IMatDataHandler.getInstance();
        previousShoppingCart = new ArrayList<>();
        shoppingItemMap = getShoppingItemMap();

//        initDebugTestData();

        if (isFirstRun()) {
            getCreditCard().setValidMonth(0);
            getCreditCard().setValidYear(0);
        }

    }

    private Map<String,ShoppingItem> getShoppingItemMap() {
        Map<String,ShoppingItem> map = new HashMap<>();
        for (Product product : getProducts()) {
            map.put(product.getName(), new ShoppingItem(product, 0));
        }
        for (ShoppingItem item : getShoppingCart().getItems()) {
            map.put(item.getProduct().getName(), item);
        }

        return map;
    }

    private void initDebugTestData() {
        reset();
        placeTestOrders();
        //fillShoppingCart();
    }

    private void fillShoppingCart() {
        for (int i = 10; i < 18; i++) {
            getShoppingCart().addProduct(getProduct(i));
        }

    }

    private void placeTestOrders() {
        for (int i = 1; i < 11; i++) {
            Product product = getProduct(i);
            ShoppingItem item = new ShoppingItem(product);
            getShoppingCart().addItem(item);
        }
        placeOrder();
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public void shutDown() {
        parent.shutDown();
    }

    public boolean isFirstRun() {
        return parent.isFirstRun();
    }

    public void resetFirstRun() {
        parent.resetFirstRun();
    }

    public boolean isCustomerComplete() {
        return parent.isCustomerComplete();
    }

    public void reset() {
        parent.reset();
    }

    public Customer getCustomer() {
        return parent.getCustomer();
    }

    public User getUser() {
        return parent.getUser();
    }

    public CreditCard getCreditCard() {
        return parent.getCreditCard();
    }

    public ShoppingCart getShoppingCart() {
        return parent.getShoppingCart();
    }

    public Collection<ShoppingItem> getShoppingItems() {
        return shoppingItemMap.values();
    }

    public Order placeOrder() {

        List<ShoppingItem> items = copyShoppingCartContent();
        clearShoppingCart();
        previousShoppingCart.clear();

        for (ShoppingItem item : items) {
            getShoppingCart().addItem(item);
        }

        return parent.placeOrder();
    }

    public Order placeOrder(boolean clearShoppingCart) {
        if (clearShoppingCart) {
            return placeOrder();
        } else {
            return parent.placeOrder(false);
        }
    }

    /**
     * Calculate the sum of all items in shopping cart, with discount and transport cost
     * @return the total cost as a double of all items in the shopping cart.
     */
    public double getPriceTotal(){
        return getPriceTotal(getShoppingCart().getItems());
    }

    /**
     * Calculates the total sum of an order, with discount and transport cost
     * @param order the order to be used for calculating the total cost
     *              of said order.
     * @return the total cost as a double of the specified order.
     */
    public double getPriceTotal(Order order) {
        return getPriceTotal(order.getItems());
    }
    /**
     * Calculates the total sum of an order, with discount and transport cost
     * @param items all items to be used for calculating the total cost.
     * @return the total cost as a double of the specified items.
     */
    public double getPriceTotal(List<ShoppingItem> items){
        double price = getPrice(items);
        return price + getDiscount(price) + getTransportCost();
    }

    /**
     * Calculates the price of all items
     * @param items list of ShoppingItems
     * @return the total cost as a double of the specified items
     */
    public double getPrice(List<ShoppingItem> items){
        double sum = 0;
        for (ShoppingItem item : items ) {
            sum += item.getTotal();
        }
        return sum;
    }

    /**
     * Calculate a discount based on the total price
     * @return discount as a negative value, data type double
     */
    public double getDiscount(double price){
        return -1 * Math.floor(price * 0.05);
    }

    /**
     * Transport cost
     * @return cost for the transport as data type double
     */
    public double getTransportCost(){
        return 50;
    }
    public List<Order> getOrders() {
        return parent.getOrders();
    }

    public Product getProduct(int idNbr) {
        return parent.getProduct(idNbr);
    }

    public List<Product> getProducts() {
        return parent.getProducts();
    }

    public List<Product> getProducts(ProductCategory pc) {
        return parent.getProducts(pc);
    }

    public List<Product> findProducts(String s) {
        return parent.findProducts(s);
    }

    public void addProduct(Product p) {
        parent.addProduct(p);
    }

    public void removeProduct(Product p) {
        parent.removeProduct(p);
    }

    public void addFavorite(Product p) {
        parent.addFavorite(p);
    }

    public void addFavorite(int idNbr) {
        parent.addFavorite(idNbr);
    }

    public void removeFavorite(Product p) {
        parent.removeFavorite(p);
    }

    public boolean isFavorite(Product p) {
        return parent.isFavorite(p);
    }

    public List<Product> favorites() {
        return parent.favorites();
    }

    public boolean hasImage(Product p) {
        return parent.hasImage(p);
    }

    public Image getFXImage(Product p) {
        return parent.getFXImage(p);
    }

    public Image getFXImage(Product p, double width, double height) {
        return parent.getFXImage(p, width, height);
    }

    public Image getFXImage(Product p, double requestedWidth, double requestedHeight, boolean preserveRatio) {
        return parent.getFXImage(p, requestedWidth, requestedHeight, preserveRatio);
    }

    public void setProductImage(Product p, File f) {
        parent.setProductImage(p, f);
    }

    public String imatDirectory() {
        return parent.imatDirectory();
    }

    private HashMap<String, ProductCategory> categoryMap = createCategoryMap();

    public HashMap<String, ProductCategory> createCategoryMap(){
        HashMap<String, ProductCategory> categoryMap = new HashMap<>();
        categoryMap.put("bär",              ProductCategory.BERRY           );
        categoryMap.put("bröd",             ProductCategory.BREAD           );
        categoryMap.put("sallad",           ProductCategory.CABBAGE         );
        categoryMap.put("citrus frukter",   ProductCategory.CITRUS_FRUIT    );
        categoryMap.put("kalla drycker",    ProductCategory.COLD_DRINKS     );
        categoryMap.put("mjölkprodukter",   ProductCategory.DAIRIES         );
        categoryMap.put("exotiska frukter", ProductCategory.EXOTIC_FRUIT    );
        categoryMap.put("fisk",             ProductCategory.FISH            );
        categoryMap.put("mjöl socker salt", ProductCategory.FLOUR_SUGAR_SALT);
        categoryMap.put("frukt",            ProductCategory.FRUIT           );
        categoryMap.put("kryddor",          ProductCategory.HERB            );
        categoryMap.put("varma drycker",    ProductCategory.HOT_DRINKS      );
        categoryMap.put("kött",             ProductCategory.MEAT            );
        categoryMap.put("meloner",          ProductCategory.MELONS          );
        categoryMap.put("nötter och frön",  ProductCategory.NUTS_AND_SEEDS  );
        categoryMap.put("pasta",            ProductCategory.PASTA           );
        categoryMap.put("baljväxter",       ProductCategory.POD             );
        categoryMap.put("potatis ris",      ProductCategory.POTATO_RICE     );
        categoryMap.put("rotfrukter",       ProductCategory.ROOT_VEGETABLE  );
        categoryMap.put("godis",            ProductCategory.SWEET           );
        categoryMap.put("grönsaksfrukter",  ProductCategory.VEGETABLE_FRUIT );
        return categoryMap;
    }
    public ProductCategory getCategory(String category){
        return categoryMap.get(category);
    }

    /**
     * Search for ProductCategories that contains searchText.
     * @param searchText the string to search for
     * @return a list of matching productCategories
     */
    public HashMap<String, ProductCategory> findCategories(String searchText){
        HashMap<String, ProductCategory> matchingCategoryMap = new HashMap();

        String searchRegEx = ".*\\Q" + searchText + "\\E.*";
        for(String key : categoryMap.keySet()) {
            if(key.matches(searchRegEx)) {
                matchingCategoryMap.put(key, categoryMap.get(key));
                //productCategories.add(categoryMap.get(key));
            }
        }
        return matchingCategoryMap;
    }

    /**
     * Removes an amount of 1 of the given product from the shopping cart
     * @param product the product that will be removed
     */
    public void removeFromShoppingCart(Product product) {
        ShoppingItem shoppingItem = getItemFromCart(product);
        if (shoppingItem != null) {
            removeFromShoppingCart(shoppingItem);
        }
    }

    /**
     * Removes an amount of 1 of the Product in given ShoppingItem from the shopping cart
     * @param shoppingItem the ShoppingItem containing the product that will be removed
     */
    public void removeFromShoppingCart(ShoppingItem shoppingItem) {
        shoppingItem.setAmount(Math.max(shoppingItem.getAmount() - 1, 0));
        getShoppingCart().fireShoppingCartChanged(shoppingItem, false);
    }

    public void deleteFromShoppingCart(ShoppingItem shoppingItem) {
        shoppingItem.setAmount(0);
        getShoppingCart().fireShoppingCartChanged(shoppingItem, false);
    }

    /**
     * Adds an amount of 1 of the given product to the shopping cart
     * @param product the product that will be added
     */
    public void addToShoppingCart(Product product) {
        ShoppingItem shoppingItem = getItemFromCart(product);
        if (shoppingItem == null) {
            shoppingItem = new ShoppingItem(product);
            getShoppingCart().addItem(shoppingItem);
        } else {
            shoppingItem.setAmount(shoppingItem.getAmount() + 1);
            getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        }
    }

    /**
     * Adds an amount of 1 of the Product in the given ShoppingItem to the shopping cart
     * @param shoppingItem the ShoppingItem containing the product that will be added
     */
    public void addToShoppingCart(ShoppingItem shoppingItem) {
        shoppingItem.setAmount(shoppingItem.getAmount() + 1);

        if (!getShoppingCart().getItems().contains(shoppingItem)) {
            getShoppingCart().addItem(shoppingItem);
        } else {
            getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        }


    }

    /**
     *
     * @param product
     * @return the first ShoppingItem containing the given Product in the ShoppingCart if found.
     *         Else null
     */
    private ShoppingItem getItemFromCart(Product product) {
        for (ShoppingItem item : getShoppingCart().getItems()) {
            if (product.getName().equalsIgnoreCase(item.getProduct().getName())){
                return item;
            }
        }
        return null;
    }

    //Shopping cart methods


    public List<ShoppingItem> getShoppingCartItems() {
        cleanShoppingCart();
        return getShoppingCart().getItems();
    }

    /**
     * Removes all ShoppingItems with amount 0 or less
     */
    private void cleanShoppingCart() {
        List<ShoppingItem> contentList = getShoppingCart().getItems();
        List<ShoppingItem> trash = new ArrayList<>();

        for (ShoppingItem item : contentList) {
            if (item.getAmount() <= 0) {
                trash.add(item);
            }
        }
        contentList.removeAll(trash);
    }

    public List<ShoppingItem> restorePreviousShoppingCart() {
        clearShoppingCart();
        ShoppingItem itemToRestore;

        for (ShoppingItem shoppingItem : previousShoppingCart) {
            itemToRestore = shoppingItemMap.get(shoppingItem.getProduct().getName());
            itemToRestore.setAmount(shoppingItem.getAmount());

            getShoppingCart().addItem(itemToRestore);

        }

        return getShoppingCart().getItems();
    }

    private List<ShoppingItem> copyShoppingCartContent() {
        ShoppingItem shoppingItemCopy;
        List<ShoppingItem> arrayCopy = new ArrayList<>();

        for (ShoppingItem shoppingItem : getShoppingCart().getItems()) {
            shoppingItemCopy = new ShoppingItem(shoppingItem.getProduct(), shoppingItem.getAmount());
            arrayCopy.add(shoppingItemCopy);
        }

        return arrayCopy;
    }

    public void safeClearShoppingCart() {
        previousShoppingCart.clear();
        ShoppingItem shoppingItemCopy;

        for (ShoppingItem shoppingItem : getShoppingCart().getItems()) {
            shoppingItemCopy = new ShoppingItem(shoppingItem.getProduct(), shoppingItem.getAmount());
            previousShoppingCart.add(shoppingItemCopy);
        }
        clearShoppingCart();
    }

    public boolean havePreviousShoppingCart() {
        return !previousShoppingCart.isEmpty();
    }


    public void clearShoppingCart() {
        for (ShoppingItem shoppingItem : getShoppingCart().getItems()) {
            shoppingItem.setAmount(0);
        }
        getShoppingCart().clear();
    }

    public boolean shoppingCartIsEmpty() {
        for (ShoppingItem shoppingItem : getShoppingCart().getItems()) {
            if (shoppingItem.getAmount() != 0) {
                return false;
            }
        }
        return true;
    }

    public double getShoppingCartTotal() {
        return getShoppingCart().getTotal();
    }

    public int getNrOfProductsInShoppingCart() {
        int nrOfProducts = 0;
        for (ShoppingItem item : getShoppingCart().getItems()) {
            nrOfProducts += item.getAmount();
        }
        return nrOfProducts;
    }
}
