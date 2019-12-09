package Application;


import ApplicationModels.Property;
import MethodModels.propertyModel;
import ApplicationModels.LinkListObjects;

import java.io.*;
import java.net.URL;
import java.rmi.server.ExportException;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TextField txtCounty;
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
    @FXML
    private TableView tblView;
    @FXML
    private TableColumn<Property, Integer> idColumn;
    @FXML
    private TableColumn<Property, Double> priceColumn;
    @FXML
    private TableColumn<Property, String> countyColumn;
    @FXML
    private TableColumn<Property, String> addressColumn;
    @FXML
    private TableColumn<Property, String> eircodeColumn;

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


    public void handleSearchButton(ActionEvent e) throws Exception {
        String category = categorySelect.getValue();
        String location = locationGeneralSelect.getValue();
        String minPrice = minPriceSelect.getValue();
        String maxPrice = maxPriceSelect.getValue();

        ArrayList<Property>tempProperty = new ArrayList<>();
        tempProperty = property.propertySearch(category, location, minPrice, maxPrice);

        ObservableList<Property> Newdata = FXCollections.observableArrayList(tempProperty);

        idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyId"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));
        countyColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("locationGeneral"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
        eircodeColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("eircode"));

        tblView.setItems(Newdata);
    }

    public void handleLoadPropertyBtn(ActionEvent e) throws Exception {
     try{
         property.loadProperty();
     }
     catch (Exception s){
         txtFeedBack.setText("Cannot Load Propertys");
     }
    }

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

    public void handleSystemExitBtn(ActionEvent e) throws Exception{
        System.exit(0);
    }

    public void handleLoginAgentBtn(ActionEvent e) throws Exception{
        Main.set_pane(6);
    }

    public void handleLogOutBtn(ActionEvent e) throws Exception{
        Main.set_pane(0);
    }

    public void handleViewDetailProperty(ActionEvent e) throws Exception{
        Main.set_pane(8);
    }


    ObservableList<String> categories = FXCollections.observableArrayList("Any", "Apartment", "Semi-Detached House", "Industrial", "Attached House", "Detached House");

    ObservableList<String> locations = FXCollections.observableArrayList("Any", "Co.Waterford", "Co.Kilkenny", "Co.Cork", "Co.Limerick");

    ObservableList<String> minPrice = FXCollections.observableArrayList("No Min", "50000", "100000", "150000", "200000", "250000", "300000", "350000", "400000", "450000", "500000", "550000", "600000");

    ObservableList<String> maxPrice = FXCollections.observableArrayList("No Max", "50000", "100000", "150000", "200000", "250000", "300000", "350000", "400000", "450000", "500000", "550000", "600000");


    @Override

    public void initialize(URL location, ResourceBundle resources) {

        try {
            LinkListObjects tempPropertys;
            ArrayList<Property> tableProperty = new ArrayList<>();
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("saveFiles/property.xml"));
            tempPropertys = (LinkListObjects) is.readObject();
            is.close();
            for (int i = 0; i < tempPropertys.size(); i++) {
                Property forProperty = (Property) tempPropertys.get(i);
                tableProperty.add(forProperty);
            }

            ObservableList<Property> data = FXCollections.observableArrayList(tableProperty);

            idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyId"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));
            countyColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("locationGeneral"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
            eircodeColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("eircode"));



            tblView.setItems(data);
        }

        catch (Exception e){
            txtFeedBack.setText("Could Not Load Propertys");
        }



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
