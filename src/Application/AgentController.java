package Application;


import ApplicationModels.LinkListObjects;
import ApplicationModels.Property;
import ApplicationModels.propertyAgent;
import MethodModels.propertyModel;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AgentController {
    protected propertyModel property;

    LinkListObjects agents = new LinkListObjects();

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextArea txtFeedBack;

    public void handleLoginBtn(ActionEvent e) throws Exception {
        if(txtUsername.getText().length()<4 || txtPassword.getText().length()<4 ){
            txtFeedBack.setText("Username and Password need \nto be 4 characters or more");
        }
        else if(login(txtUsername.getText(),txtPassword.getText())){
            txtUsername.setText("");
            txtPassword.setText("");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/FXML/homeScreenAgent.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
        else {
            txtPassword.clear();
        }
    }

    private boolean login(String username, String password) {
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("saveFiles/agents.xml"));
            agents = (LinkListObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new LinkListObjects();
            txtFeedBack.setText("Password File not located\n" + e);
            return false;

        }
        catch (Exception e) {
            txtFeedBack.setText("Error accessing Password File\n" + e);
            return false;
        }

        for (int i = 0; i < agents.size(); i++) {
            propertyAgent agentLogin = (propertyAgent) agents.get(i);
            if (agentLogin.getUsername().contains(username) && agentLogin.getPassword().contains(password))
                return true;
        }
        return false;
    }

    public void handleAddAgentBtn(ActionEvent e) throws Exception{
        Main.set_pane(2);
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

}
