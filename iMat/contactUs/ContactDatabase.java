package iMat.contactUs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class ContactDatabase {
    private Set<Contact> contacts;
    private static ContactDatabase sharedInstance;

    private ContactDatabase() {
        File file = new File("src/iMat/resources/kontaktuppgifter.txt");
        BufferedReader dis = null;
        this.contacts = new HashSet<>();

        try {
            FileReader reader = new FileReader(file);
            dis = new BufferedReader(reader);
            String line = null;
            String name = null;
            String email = null;
            String phoneNumber = null;
            String imagePath = null;

            while (true) {
                while ((line = dis.readLine()) != null) {
                    if (line.equals("#name")) {
                        name = dis.readLine();
                    } else if (line.equals("#image")) {
                        imagePath = "src/iMat/resources/" + dis.readLine();
                    } else if (line.equals("#email")) {
                        email = dis.readLine();
                    } else if (line.equals("#phonenumber")) {
                        phoneNumber = dis.readLine();
                        this.contacts.add(new Contact(name, imagePath, email, phoneNumber));
                    }
                }
                dis.close();
                reader.close();
                break;
            }
        } catch (Exception var21) {
            System.out.println(var21.getMessage());
        }
    }

    public static ContactDatabase getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new ContactDatabase();
        }
        return sharedInstance;
    }

    public List<Contact> contacts() {
        List<Contact> contactList = new ArrayList<>();
        Iterator<Contact> it = this.contacts.iterator();
        int var = 0;
        while (it.hasNext()) {
            ++var;
            Contact c = (Contact) it.next();
            contactList.add(c);
        }
        return contactList;
    }
}
