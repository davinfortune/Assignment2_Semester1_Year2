package registerControllers;

import Application.Main;
import ApplicationModels.LinkListObjects;
import ApplicationModels.propertyAdmin;
import ApplicationModels.propertyAgent;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class registerAdminController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private TextField txtAdminID;
    @FXML
    private TextField txtFullName;
    @FXML
    private PasswordField txtMasterPassword;
    @FXML
    private TextArea txtAreaFeedback;

    public void handleAdminRegisterBtn(ActionEvent e) throws Exception {
        try {
            LinkListObjects tempPropertys;
            ArrayList<propertyAdmin> tempArray = new ArrayList<>();
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("saveFiles/admins.xml"));
            tempPropertys = (LinkListObjects) is.readObject();
            is.close();
            for (int i = 0; i < tempPropertys.size(); i++) {
                propertyAdmin forProperty = (propertyAdmin) tempPropertys.get(i);
                tempArray.add(forProperty);
            }
            for (int i = 0; i < tempArray.size(); i++) {
                if(txtAdminID.getText().equals(tempArray.get(i).getAdminId())){
                    txtAreaFeedback.setText("ID already taken");
                    return;
                }
            }
        }
        catch (Exception t) {
            txtAreaFeedback.setText("Error with id");
        }

        try {
            LinkListObjects tempPropertys;
            ArrayList<propertyAdmin> tempArray = new ArrayList<>();
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("saveFiles/admins.xml"));
            tempPropertys = (LinkListObjects) is.readObject();
            is.close();
            for (int i = 0; i < tempPropertys.size(); i++) {
                propertyAdmin forProperty = (propertyAdmin) tempPropertys.get(i);
                tempArray.add(forProperty);
            }
            for (int i = 0; i < tempArray.size(); i++) {
                if(txtUsername.getText().equals(tempArray.get(i).getUsername())){
                    txtAreaFeedback.setText("Username already taken");
                    return;
                }
            }
        }
        catch (Exception t) {
            txtAreaFeedback.setText("Error with username");
        }

        if(txtMasterPassword.getText().equals("root")) {
            if (txtUsername.getText().length() < 4 || txtPassword.getText().length() < 4) {
                txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
            } else if (!txtPassword.getText().equals(txtRepeatPassword.getText())) {
                txtAreaFeedback.setText("Password must match RepeatPassword");
            } else if (register(txtUsername.getText(), txtPassword.getText(), txtAdminID.getText(), txtFullName.getText())) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/FXML/homeScreenAdmin.fxml"));
                Parent tableViewParent = loader.load();

                Scene tableViewScene = new Scene(tableViewParent);

                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }
        }
        else{
            txtAreaFeedback.setText("unSuccessful Registration: Wrong Master Password");
        }
    }

    private boolean register(String username, String password, String adminID, String fullName) {
        LinkListObjects admins = new LinkListObjects();
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("saveFiles/admins.xml"));
            admins = (LinkListObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            admins =  new LinkListObjects();
            txtAreaFeedback.setText("New Password File");
        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }


        try {
            propertyAdmin admin = new propertyAdmin(username, password, adminID, fullName);
            admins.add(admin);
            Main.setAdmin(admin);
            ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("saveFiles/admins.xml"));
            out.writeObject(admins);
            out.close();
            return true;
        } catch (Exception e) {
            txtAreaFeedback.setText("Error writing to Password File\n" + e);
            return false;
        }
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenGeneral.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
