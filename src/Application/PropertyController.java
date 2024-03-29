package Application;


import ApplicationModels.Property;
import MethodModels.propertyModel;
import ApplicationModels.LinkListObjects;


import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
    private TextField txtCategory;
    @FXML
    private TextField txtCounty;
    @FXML
    private TextField txtDeleteIndex;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TextField txtPath;

    @FXML
    private TextArea txtlistAllProperties;
    @FXML
    private TextArea txtlistAllAgentDetails;
    @FXML
    private TextArea txtAreaAllAgentInfo;
    @FXML
    private TextArea txtAreaPropertyInfo;
    @FXML
    private ImageView propertyImage;
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
    private TableColumn<Property, String> descriptionColumn;
    @FXML
    private TableColumn<Property, String> townColumn;
    @FXML
    private TableColumn<Property, String> categoryColumn;
    @FXML
    private TableColumn<Property, Double> priceColumn;
    @FXML
    private TableColumn<Property, String> countyColumn;
    @FXML
    private TableColumn<Property, String> addressColumn;
    @FXML
    private TableColumn<Property, String> eircodeColumn;

    private String imageSelectedLabel;
    private String noImage = "No Image";


    public void handleAddPropertyBtn(ActionEvent e) throws Exception {
        int propertyId;
        try {
            propertyId = Integer.parseInt(txtId.getText());
            LinkListObjects tempPropertys;
            ArrayList<Property> tempArray = new ArrayList<>();
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("saveFiles/property.xml"));
            tempPropertys = (LinkListObjects) is.readObject();
            is.close();
            for (int i = 0; i < tempPropertys.size(); i++) {
                Property forProperty = (Property) tempPropertys.get(i);
                tempArray.add(forProperty);
            }
            for (int i = 0; i < tempArray.size(); i++) {
                if(propertyId == tempArray.get(i).getPropertyId()){
                    txtFeedBack.setText("ID already taken");
                    return;
                }
            }
        }
        catch (Exception t) {
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

            if(txtPath.getText().trim().equals("")) {
                if (property.addProperty(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price, noImage)) {
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
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/FXML/homeScreenAgent.fxml"));
                    Parent tableViewParent = loader.load();

                    Scene tableViewScene = new Scene(tableViewParent);

                    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                    window.setScene(tableViewScene);
                    window.show();
                } else {
                    txtFeedBack.setText("Property Not Added");
                }
            }
            else {
                if (property.addProperty(propertyId, description, address, propertyType, locationGeneral, locationSpecific, BER, Eircode, price, imageSelectedLabel)) {
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
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/FXML/homeScreenAgent.fxml"));
                    Parent tableViewParent = loader.load();

                    Scene tableViewScene = new Scene(tableViewParent);

                    Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                    window.setScene(tableViewScene);
                    window.show();
                } else {
                    txtFeedBack.setText("Property Not Added");
                }
            }
        }



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

    public void handleRegisterAdminButton(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/registerAdmin.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleLoginAdminButton(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/loginAdmin.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleSystemExitBtn(ActionEvent e) throws Exception{
        System.exit(0);
    }

    public void handleLoginAgentBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/loginAgent.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void handleReturnHomeBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenGeneral.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void changeSceneToDetailedViewBtn(ActionEvent e) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/viewAllProperty.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        showImageController controller = loader.getController();
        Property propertyToDisplay =(Property)tblView.getSelectionModel().getSelectedItem();
        if(propertyToDisplay == null)
            return;

        controller.initData(propertyToDisplay);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void selectImageButtonPressed(ActionEvent event) throws Exception {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Property Pictures");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        File source = fileChooser.showOpenDialog(stage);

        File destination = new File("src/img/"+source.getName());
        imageSelectedLabel = "/img/" + source.getName();

        if (source != null) {
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try {
                sourceChannel = new FileInputStream(source).getChannel();
                destChannel = new FileOutputStream(destination).getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            }
            catch(IOException e){
                txtFeedBack.setText("IOException Image Selection");
            }
            catch (Exception e){
                txtFeedBack.setText("Exception Image Selection");

            }
            finally{
                try {
                    sourceChannel.close();
                    destChannel.close();
                    txtFeedBack.setText("Channels closed");
                }
                catch(IOException e){
                    txtFeedBack.setText("IOException Close Channel");
                }
                catch (Exception e){
                    txtFeedBack.setText("Exception Close Channel");
                }
            }
            txtPath.setText(source.getAbsolutePath());
        }
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
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
          } catch (Exception e) {
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
