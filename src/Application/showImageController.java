package Application;

import ApplicationModels.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class showImageController {
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
    private TextField txtCategory;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private ImageView propertyImage;

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
        txtAddress.setText(property.getDescription());
        txtlocationSpecific.setText(""+property.getLocationSpecific());
        txtBER.setText(""+property.getBER());
        txtCategory.setText(property.getCategory());
        txtCounty.setText(property.getLocationGeneral());
        txtEircode.setText(property.getEircode());
        txtPrice.setText(Double.toString(property.getPrice()));
    }



    public void changeScreenButtonPushed(ActionEvent event) throws IOException
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/FXML/homeScreenGeneral.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
