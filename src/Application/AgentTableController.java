package Application;

import ApplicationModels.LinkListObjects;
import ApplicationModels.Property;
import MethodModels.propertyModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AgentTableController implements Initializable {
    protected propertyModel property;

    @FXML
    private TextArea txtFeedBack;
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




    public void handleAddAgentBtn(ActionEvent e) throws Exception{
        Main.set_pane(2);
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception{
        Main.set_pane(0);
    }

    ObservableList<String> categories = FXCollections.observableArrayList("Any", "Apartment", "Semi-Detached House", "Industrial", "Attached House", "Detached House");

    ObservableList<String> locations = FXCollections.observableArrayList("Any", "Co.Waterford", "Co.Kilkenny", "Co.Cork", "Co.Limerick");

    ObservableList<String> minPrice = FXCollections.observableArrayList("No Min", "50000", "100000", "150000", "200000", "250000", "300000", "350000", "400000", "450000", "500000", "550000", "600000");

    ObservableList<String> maxPrice = FXCollections.observableArrayList("No Max", "50000", "100000", "150000", "200000", "250000", "300000", "350000", "400000", "450000", "500000", "550000", "600000");

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        Main.primaryStage.setScene(new Scene(root, 700, 550));
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
