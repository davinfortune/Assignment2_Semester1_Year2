package Application;


import MethodModels.propertyModel;
import ApplicationModels.LinkListObjects;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.function.DoubleBinaryOperator;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DocumentReader;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;

import javax.swing.*;

public class PropertyController implements Initializable {
    protected propertyModel property;

    String category, locationGeneral;
    String min, max;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtlocationSpecific;
    @FXML
    private TextField txtBER;
    @FXML
    private TextField txtEircode;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtDeleteIndex;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TextArea txtlistAllProperties;
    @FXML
    private TextArea txtlistAllAgentDetails;
    @FXML
    private TextArea txtAreaAllAgentInfo;
    @FXML
    private TextArea txtAreaPropertyInfo;
    @FXML
    private Button updateButton;
    @FXML
    private ComboBox<String> categorySelect = new ComboBox<>();
    @FXML
    private ComboBox<String> locationGeneralSelect = new ComboBox<>();
    @FXML
    private ComboBox<String> minPriceSelect = new ComboBox<>();
    @FXML
    private ComboBox<String> maxPriceSelect = new ComboBox<>();

    public void handleReadBtn(ActionEvent e) {

    }


    public void handleAddPropertyBtn(ActionEvent e) throws Exception {
        try {
            int propertyId;
            try {
                propertyId = Integer.parseInt(txtId.getText());
            } catch (Exception t) {
                txtFeedBack.setText("Please enter an Integer");
                propertyId = Integer.parseInt(null);
            }
            String description = txtDescription.getText();

            String address = txtAddress.getText();

            String propertyType = categorySelect.getValue();

            String locationGeneral = locationGeneralSelect.getValue();

            String locationSpecific = txtlocationSpecific.getText();

            String BER = txtBER.getText();

            String Eircode = txtEircode.getText();

            double price = Double.parseDouble(txtPrice.getText());

            if(property.addProperty(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price)){
                txtId.setText("");
                txtDescription.setText("");
                txtAddress.setText("");
                categorySelect.setValue("Any");
                locationGeneralSelect.setValue("Any");
                txtlocationSpecific.setText("");
                txtBER.setText("");
                txtEircode.setText("");
                txtPrice.setText("");
                txtFeedBack.setText("Property Added");
                Main.set_pane(0);
            }
            else {
                txtFeedBack.setText("Property Not Added");
            }

        }
        catch(Exception d){
            txtFeedBack.setText("Please Make sure you\nentered Everything correctly\n" + d);
        }
    }


/*
    public void handleReadPropertyBtn(ActionEvent e) throws Exception{
        int index = Integer.parseInt(txtDeleteIndex.getText());
        if(property. == 0){
            if(index <= property.propertys.size()) {
                property.loadProperty();
                txtAreaPropertyInfo.setText(property.listAllLowDetailProperties());

                txtId.setText(Integer.toString(property.propertys.get(index).getPropertyId()));
                txtDescription.setText(property.propertys.get(index).getDescription());
                txtAddress.setText(property.propertys.get(index).getAddress());
                categorySelect.setValue(property.propertys.get(index).getCategory());
                locationGeneralSelect.setValue(property.propertys.get(index).getLocationGeneral());
                txtlocationSpecific.setText(property.propertys.get(index).getLocationSpecific());
                txtBER.setText(property.propertys.get(index).getBER());
                txtEircode.setText(property.propertys.get(index).getEircode());
                txtPrice.setText(Double.toString(property.propertys.get(index).getPrice()));
            }
            else{
                txtFeedBack.setText("Please enter a vaild Property Number");
            }
        }
        else{
            if(index <= property.propertys.size()) {
                txtId.setText(Integer.toString(property.propertys.get(index).getPropertyId()));
                txtDescription.setText(property.propertys.get(index).getDescription());
                txtAddress.setText(property.propertys.get(index).getAddress());
                categorySelect.setValue(property.propertys.get(index).getCategory());
                locationGeneralSelect.setValue(property.propertys.get(index).getLocationGeneral());
                txtlocationSpecific.setText(property.propertys.get(index).getLocationSpecific());
                txtBER.setText(property.propertys.get(index).getBER());
                txtEircode.setText(property.propertys.get(index).getEircode());
                txtPrice.setText(Double.toString(property.propertys.get(index).getPrice()));
            }
            else{
                txtFeedBack.setText("Please enter a vaild Property Number");
            }
        }
        updateButton.setDisable(false);
    }


    public void handleUpdatePropertyBtn(ActionEvent e) throws Exception{
        property.updateProperty(Integer.parseInt(txtDeleteIndex.getText()),Integer.parseInt(txtId.getText()), txtDescription.getText(), txtAddress.getText(), categorySelect.getValue(), locationGeneralSelect.getValue(),
                txtlocationSpecific.getText(), txtBER.getText(), txtEircode.getText(), Double.parseDouble(txtPrice.getText()));
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("property.xml"));
            out.writeObject(property.propertys);
            out.close();
        } catch (Exception t) {
            txtFeedBack.setText("Error Updating Property");

        }
        Main.set_pane(2);
    }

    public void handleDeleteButton(ActionEvent e) throws Exception{
        int index = Integer.parseInt(txtDeleteIndex.getText());
        if(index <= property.propertys.size() && index >= 0) {
            property.deleteProperty(index);
            XStream xstream = new XStream(new DomDriver());
            try {
                ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("property.xml"));
                out.writeObject(property.propertys);
                out.close();
            } catch (Exception t) {
                txtFeedBack.setText("Error Deleting the Property");
            }
        }
        else {
            txtFeedBack.setText("Please Enter a Valid \nProperty Number to Delete");
        }
        Main.set_pane(2);
        txtAreaPropertyInfo.setText(property.listAllLowDetailProperties());
    }*/

  /*  public void handleSearchButton(ActionEvent e) throws Exception {
        if (property.propertys.size() == 0) {
            try {
                property.loadProperty();
            } catch (Exception z) {
                txtFeedBack.setText("No Properties to Load");
            }
        }

        if (minPriceSelect.getValue() == "No Min" && maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() != "Any" && locationGeneralSelect.getValue() != "Any") {
            category = categorySelect.getValue();
            locationGeneral = locationGeneralSelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyPropertyType(locationGeneral, category));
        }

        if (maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() != "Any") {
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            locationGeneral = locationGeneralSelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyMin(locationGeneral, minimumPrice));
        }

        if (maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() != "Any") {
            locationGeneral = locationGeneralSelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCounty(locationGeneral));
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() != "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            locationGeneral = locationGeneralSelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyMax(locationGeneral, maximumPrice));
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() != "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            if (minimumPrice > maximumPrice) {
                txtFeedBack.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                locationGeneral = locationGeneralSelect.getValue();
                txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyMinMax(locationGeneral, minimumPrice, maximumPrice));
            }
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() != "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            if (minimumPrice > maximumPrice) {
                txtFeedBack.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                category = categorySelect.getValue();
                locationGeneral = locationGeneralSelect.getValue();
                txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyMinMaxType(locationGeneral, minimumPrice, maximumPrice, category));
            }
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() == "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            if (minimumPrice > maximumPrice) {
                txtFeedBack.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                txtAreaPropertyInfo.setText(property.listAllPropertiesSearchMinMax(minimumPrice, maximumPrice));
            }
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() == "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            if (minimumPrice > maximumPrice) {
                txtFeedBack.setText("Please Check you Minimum\nand Maximum values are correct");
            } else {
                category = categorySelect.getValue();
                txtAreaPropertyInfo.setText(property.listAllPropertiesSearchMinMaxType(minimumPrice, maximumPrice,category));
            }
        }

        if (maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() == "Any") {
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            category = categorySelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchMinType(minimumPrice, category));
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() == "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            category = categorySelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchMaxType(maximumPrice, category));
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() != "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            category = categorySelect.getValue();
            locationGeneral = locationGeneralSelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyMaxType(locationGeneral,maximumPrice, category));
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() == "Any") {
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchMax(minimumPrice));
        }

        if (maxPriceSelect.getValue() != "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() == "Any") {
            max = maxPriceSelect.getValue();
            double maximumPrice = Double.parseDouble(max);
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchMin(maximumPrice));
        }

        if (maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() == "Any") {
            category = categorySelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchType(category));
        }

        if (maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() != "Any" && minPriceSelect.getValue() != "No Min" && locationGeneralSelect.getValue() != "Any") {
            min = minPriceSelect.getValue();
            double minimumPrice = Double.parseDouble(min);
            category = categorySelect.getValue();
            locationGeneral = locationGeneralSelect.getValue();
            txtAreaPropertyInfo.setText(property.listAllPropertiesSearchCountyMinType(locationGeneral,minimumPrice, category));
        }
        if(maxPriceSelect.getValue() == "No Max" && categorySelect.getValue() == "Any" && minPriceSelect.getValue() == "No Min" && locationGeneralSelect.getValue() == "Any"){
            txtAreaPropertyInfo.setText(property.listAllLowDetailProperties());
        }
    }*/

    public void handleLoadPropertyBtn(ActionEvent e) throws Exception {
     try{
         property.loadProperty();
     }
     catch (Exception s){
         txtFeedBack.setText("Cannot Load Propertys");
     }
    }

/*    public void handleListDetailsButton(ActionEvent e) throws Exception {
        try {
            txtlistAllProperties.setText(property.listDetailProperties(Integer.parseInt(txtDeleteIndex.getText())));
        }
        catch (Exception t){
            txtFeedBack.setText("Invalid value Entered");
        }
    }*/

    public void handleAddPropertyButton(ActionEvent e) throws Exception{
        Main.set_pane(1);
    }

    public void handleRegisterAgentButton(ActionEvent e) throws Exception{
        Main.set_pane(2);
    }

    public void handleRegisterAdminButton(ActionEvent e) throws Exception{
        Main.set_pane(3);
    }

    public void handleLoginAdminButton(ActionEvent e) throws Exception{
        Main.set_pane(4);
    }

    ObservableList<String> categories = FXCollections.observableArrayList("Any", "Apartment", "Semi-Detached House", "Industrial", "Attached House", "Detached House");

    ObservableList<String> locations = FXCollections.observableArrayList("Any", "Co.Waterford", "Co.Kilkenny", "Co.Cork", "Co.Limerick");

    ObservableList<String> minPrice = FXCollections.observableArrayList("No Min", "50000", "100000", "150000", "200000", "250000", "300000", "350000", "400000", "450000", "500000", "550000", "600000");

    ObservableList<String> maxPrice = FXCollections.observableArrayList("No Max", "50000", "100000", "150000", "200000", "250000", "300000", "350000", "400000", "450000", "500000", "550000", "600000");


    @Override

    public void initialize(URL location, ResourceBundle resources) {

        property = new propertyModel();

        categorySelect.setItems(categories);

        locationGeneralSelect.setItems(locations);

        minPriceSelect.setItems(minPrice);

        maxPriceSelect.setItems(maxPrice);

        categorySelect.setValue("Any");

        locationGeneralSelect.setValue("Any");

        minPriceSelect.setValue("No Min");

        maxPriceSelect.setValue("No Max");
    }
}
