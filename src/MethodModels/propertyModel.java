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

    public boolean addProperty(int propertyId, String description, String address, String propertyType, String locationGeneral, String locationSpecific, String BER, String Eircode, double price,String image){
        if(property.isEmpty()) {
            try {
                loadProperty();
                Property propertyLocal = new Property(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price, image);
                saveProperty(propertyLocal);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                Property propertyLocal = new Property(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price, image);
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

    public ArrayList<Property> propertySearch(String propertyType, String location, String minPrice, String maxPrice) {

         LinkListObjects tempPropertys = new LinkListObjects();
         ArrayList<Property> tableProperty = new ArrayList<>();
         try {
             XStream xstream = new XStream(new DomDriver());
             ObjectInputStream is = xstream.createObjectInputStream
                     (new FileReader("saveFiles/property.xml"));
             tempPropertys = (LinkListObjects) is.readObject();
             is.close();
         }
         catch (Exception e){

         }

        for (int i = 0; i < tempPropertys.size(); i++) {
            Property forProperty = (Property) tempPropertys.get(i);
            tableProperty.add(forProperty);
        }

        if (propertyType == "Any" && location =="Any" && minPrice == "No Min" && maxPrice == "No Max"){
            return tableProperty;
        }
        else {
            ArrayList<Property> searchResults = new ArrayList<>();

            for (int i = 0; i < tableProperty.size(); i++) {

                if (propertyType.equals("Any") && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(tableProperty.get(i));
                } else if (location.equals("Any") && tableProperty.get(i).getCategory().equals(propertyType) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(tableProperty.get(i));
                } else if (tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice.equals("No Min") && maxPrice.equals("No Max")) {
                    searchResults.add(tableProperty.get(i));
                } else if (tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice.equals("No Max")) {
                    double price = tableProperty.get(i).getPrice();
                    double checker = Double.parseDouble(minPrice);
                    if (price > checker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double checker = Double.parseDouble(maxPrice);
                    if (price < checker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if (propertyType.equals("Any") && location.equals("Any") && minPrice != "No Min" && maxPrice.equals("No Max")) {
                    double price = tableProperty.get(i).getPrice();
                    double checker = Double.parseDouble(minPrice);
                    if (price > checker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(propertyType.equals("Any") && location.equals("Any") && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double checker = Double.parseDouble(maxPrice);
                    if (price < checker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(propertyType.equals("Any") && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice == "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double checker = Double.parseDouble(minPrice);
                    if (price > checker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(propertyType.equals("Any") && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double checker = Double.parseDouble(maxPrice);
                    if (price < checker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(propertyType.equals("Any") && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(propertyType.equals("Any") && location.equals("Any") && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(tableProperty.get(i).getCategory().equals(propertyType) && location.equals("Any") && minPrice != "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price > Minchecker && price < Maxchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(tableProperty.get(i).getCategory().equals(propertyType) && location.equals("Any") && minPrice != "No Min" && maxPrice == "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    if (price > Minchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(tableProperty.get(i).getCategory().equals(propertyType) && location.equals("Any") && minPrice == "No Min" && maxPrice != "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Maxchecker = Double.parseDouble(maxPrice);
                    if (price < Maxchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                else if(tableProperty.get(i).getCategory().equals(propertyType) && tableProperty.get(i).getLocationGeneral().equals(location) && minPrice != "No Min" && maxPrice == "No Max") {
                    double price = tableProperty.get(i).getPrice();
                    double Minchecker = Double.parseDouble(minPrice);
                    if (price > Minchecker) {
                        searchResults.add(tableProperty.get(i));
                    }
                }
                }
            return searchResults;
        }
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
