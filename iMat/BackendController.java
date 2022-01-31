package iMat;

import iMat.contactUs.Contact;
import iMat.contactUs.ContactDatabase;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.List;

public class BackendController {
    private Model db = Model.getInstance();
    private List<Product> products;
    private List<Contact> contacts;
    private ProductCategory category;
    private ContactDatabase cudb = ContactDatabase.getSharedInstance();

    public List<Product> getProducts() {
        products = db.getProducts();
        return products;
    }

    public List<Contact> getContacts() {
        contacts = cudb.contacts();
        return contacts;
    }

    public void setCustomerAddress(String address) {
        db.getCustomer().setAddress(address);
    }

    public void setCostumerEmail(String email) {
        db.getCustomer().setEmail(email);
    }

    public void setCustomerFirstName(String name) {
        db.getCustomer().setFirstName(name);
    }

    public void setCustomerLastName(String lastName) {
        db.getCustomer().setLastName(lastName);
    }

    public void setCustomerMobilePhoneNumber(String mobile) {
        db.getCustomer().setMobilePhoneNumber(mobile);
    }

    public void setCustomerPhoneNumber(String phoneNumber) {
        db.getCustomer().setPhoneNumber(phoneNumber);
    }

    public void setCustomerPostAddress(String postAddress) {
        db.getCustomer().setPostAddress(postAddress);
    }

    public void setCustomerPostCode(String postCode) {
        db.getCustomer().setPostCode(postCode);
    }

    public void setCategory(String category) {
        String input = category.toLowerCase();
        switch (input) {
            case "bär":
                this.category = ProductCategory.BERRY;
                break;
            case "bröd":
                this.category = ProductCategory.BREAD;
                break;
            case "sallad":
                this.category = ProductCategory.CABBAGE;
                break;
            case "citrus frukter":
                this.category = ProductCategory.CITRUS_FRUIT;
                break;
            case "kalla drycker":
                this.category = ProductCategory.COLD_DRINKS;
                break;
            case "mjölkprodukter":
                this.category = ProductCategory.DAIRIES;
                break;
            case "exotiska frukter":
                this.category = ProductCategory.EXOTIC_FRUIT;
                break;
            case "fisk":
                this.category = ProductCategory.FISH;
                break;
            case "mjöl socker salt":
                this.category = ProductCategory.FLOUR_SUGAR_SALT;
                break;
            case "frukt":
                this.category = ProductCategory.FRUIT;
                break;
            case "kryddor":
                this.category = ProductCategory.HERB;
                break;
            case "varma drycker":
                this.category = ProductCategory.HOT_DRINKS;
                ;
                break;
            case "kött":
                this.category = ProductCategory.MEAT;
                break;
            case "meloner":
                this.category = ProductCategory.MELONS;
                break;
            case "nötter och frön":
                this.category = ProductCategory.NUTS_AND_SEEDS;
                break;
            case "pasta":
                this.category = ProductCategory.PASTA;
                break;
            case "baljväxter":
                this.category = ProductCategory.POD;
                break;
            case "potatis ris":
                this.category = ProductCategory.POTATO_RICE;
                break;
            case "rotfrukter":
                this.category = ProductCategory.ROOT_VEGETABLE;
                break;
            case "godis":
                this.category = ProductCategory.SWEET;
                break;
            case "grönsaksfrukter":
                this.category = ProductCategory.VEGETABLE_FRUIT;
                break;
            default:
                this.category = null;
                break;

        }
    }


}
