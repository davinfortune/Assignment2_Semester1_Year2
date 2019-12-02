package MethodModels;

import ApplicationModels.LinkListObjects;
import ApplicationModels.Property;
import javafx.application.Application;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.fxml.FXML;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;

public class propertyModel {

    private LinkListObjects property = new LinkListObjects();

    public boolean addProperty(int propertyId, String description, String address, String propertyType, String locationGeneral, String locationSpecific, String BER, String Eircode, double price){
        if(property.isEmpty()) {
            try {
                loadProperty();
                Property propertyLocal = new Property(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price);
                saveProperty(propertyLocal);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                Property propertyLocal = new Property(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price);
                saveProperty(propertyLocal);
                return true;
            } catch (Exception t) {
                return false;
            }
        }
    }

    public String listDetailProperties(int index) {
        String displayDetail;
        Property listProperty = (Property) property.get(index);
        displayDetail = " Property Number " + index + ": \n" + listProperty;
        return displayDetail;
    }

    public String listAllLowDetailProperties() {
        String displayPropertys ="";
        for(int i = 0; i < property.size(); i++){
            Property listProperty = (Property) property.get(i);
            displayPropertys += "\nProperty Number "+ i + ": \n" + listProperty.getAddress() + "\n" + listProperty.getLocationGeneral() + "\n" +
                    "€" + listProperty.getPrice() + "\n";
    }
        return displayPropertys;
    }

    public void loadProperty() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/property.xml"));
        property = (LinkListObjects) is.readObject();
        is.close();
    }

    public void saveProperty(Property propertyLocal) throws Exception {
        XStream xstream = new XStream(new DomDriver());
        property.add(propertyLocal);
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("saveFiles/property.xml"));
        out.writeObject(property);
        out.close();
    }



}
