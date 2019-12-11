package Application;

import ApplicationModels.LinkListObjects;
import ApplicationModels.Property;
import MethodModels.propertyModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import jdk.jshell.spi.ExecutionControlProvider;

import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AgentTableController implements Initializable {
    protected propertyModel property;

    @FXML
    private int index;
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
    private TextArea txtFeedBack;
    @FXML
    private TextField txtPath;
    @FXML
    private ImageView propertyImage;
    @FXML
    private ComboBox<String> categorySelect = new ComboBox<>();
    @FXML
    private ComboBox<String> locationGeneralSelect = new ComboBox<>();
    @FXML
    private ComboBox<String> minPriceSelect = new ComboBox<>();
    @FXML
    private ComboBox<String> maxPriceSelect = new ComboBox<>();
    @FXML
    private TableView tableViewAgent;
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

    public void handleUpdatePropertyBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/updateProperty.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        AgentTableController controller = loader.getController();
        Property propertyToDisplay =(Property)tableViewAgent.getSelectionModel().getSelectedItem();


        LinkListObjects temp = new LinkListObjects();
        ArrayList<Property> tempArray = new ArrayList<>();
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/property.xml"));
        temp = (LinkListObjects) is.readObject();
        is.close();
        for (int i = 0; i < temp.size(); i++) {
            Property forProperty = (Property) temp.get(i);
            tempArray.add(forProperty);
            if(((Property) temp.get(i)).getPropertyId() == propertyToDisplay.getPropertyId()){
                index = i;
                System.out.print(index);
            }
        }
        if(propertyToDisplay == null)
            return;

        controller.initData(propertyToDisplay);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleUpdateProperty(ActionEvent e) throws Exception{
    LinkListObjects temp = new LinkListObjects();
    LinkListObjects tempSave = new LinkListObjects();
    ArrayList<Property> tempArray = new ArrayList<>();
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/property.xml"));
        temp = (LinkListObjects) is.readObject();
        is.close();
        for (int i = 0; i < temp.size(); i++) {
            Property forProperty = (Property) temp.get(i);
            tempArray.add(forProperty);
        }
        tempArray.get(index).setPropertyId(Integer.parseInt(txtId.getText()));
        tempArray.get(index).setDescription(txtDescription.getText());
        tempArray.get(index).setAddress(txtAddress.getText());
        tempArray.get(index).setLocationSpecific(txtlocationSpecific.getText());
        tempArray.get(index).setBER(txtBER.getText());
        tempArray.get(index).setEircode(txtEircode.getText());
        tempArray.get(index).setPrice(Double.parseDouble(txtPrice.getText()));
        tempArray.get(index).setCategory(categorySelect.getValue());
        tempArray.get(index).setLocationGeneral(locationGeneralSelect.getValue());
        if(txtPath.getText().trim().equals("")) {
            //do nothing
        }
        else{
            tempArray.get(index).setImagePath(imageSelectedLabel);
        }
        for (int i = 0; i < temp.size(); i++) {
            tempSave.add(tempArray.get(i));
        }
        XStream save = new XStream(new DomDriver());
        ObjectOutputStream out = save.createObjectOutputStream(new FileWriter("saveFiles/property.xml"));
        out.writeObject(tempSave);
        out.close();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenAgent.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }



    public void handleLogOutBtn(ActionEvent e) throws Exception{
        System.exit(0);
    }

    public void handleAddAgentBtn(ActionEvent e) throws Exception{
        Main.set_pane(2);
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenAgent.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

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

    public void handleSearchButton(ActionEvent e) throws Exception {
        String category = categorySelect.getValue();
        String location = locationGeneralSelect.getValue();
        String minPrice = minPriceSelect.getValue();
        String maxPrice = maxPriceSelect.getValue();

        ArrayList<Property>tempProperty = new ArrayList<>();
        tempProperty = property.propertySearch(category, location, minPrice, maxPrice);

        ObservableList<Property> Newdata = FXCollections.observableArrayList(tempProperty);

        idColumn.setCellValueFactory(new PropertyValueFactory<Property, Integer>("propertyId"));
        countyColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("locationGeneral"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("description"));
        townColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("locationSpecific"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("category"));

        tableViewAgent.setItems(Newdata);
    }

    public void initData(Property property)
    {
        Image img;
        if(property.getImagePath().equals("No Image")) {
            img = new Image("/img/default.png");
        }
        else{
            img = new Image(property.getImagePath());
        }
        propertyImage.setImage(img);

        txtId.setText(""+property.getPropertyId()); //int to String
        txtDescription.setText(property.getDescription());
        txtAddress.setText(property.getAddress());
        txtlocationSpecific.setText(""+property.getLocationSpecific());
        txtBER.setText(""+property.getBER());
        categorySelect.setValue(property.getCategory());
        locationGeneralSelect.setValue(property.getLocationGeneral());
        txtEircode.setText(property.getEircode());
        txtPrice.setText(Double.toString(property.getPrice()));
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
            countyColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("locationGeneral"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("description"));
            townColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("locationSpecific"));
            categoryColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("category"));

            tableViewAgent.setItems(data);
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
