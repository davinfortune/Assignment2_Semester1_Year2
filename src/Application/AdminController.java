package Application;

import ApplicationModels.LinkListObjects;
import ApplicationModels.propertyAgent;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class AdminController {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtPasswordAdmin;
    @FXML
    private TextField txtIndexList;
    @FXML
    private TextField txtAgentId;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtDeleteIndex;
    @FXML
    private Button deleteButton;
    @FXML
    private Button readButton;
    @FXML
    private Button updateButton;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    private TextArea txtListDetailAgent;
    @FXML
    private TextArea txtListAllAgents;
    @FXML
    LinkListObjects agents;
    public void handleLoginBtn(ActionEvent e) throws Exception {
        if(txtUsername.getText().length()<4 || txtPassword.getText().length()<4 ){
            txtAreaFeedback.setText("Username and Password need \nto be 4 characters or more");
        }
        else if(login(txtUsername.getText(),txtPassword.getText())){
            txtAreaFeedback.setText("Successful Login");
            Main.set_pane(2);
        }
        else {
            txtAreaFeedback.setText("Un-Successful Login");
            txtPassword.clear();
        }
    }

    private boolean login(String username, String password) {
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("safeFiles/agents.xml"));
            agents = (LinkListObjects) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new LinkListObjects();
            txtAreaFeedback.setText("Password File not located\n" + e);
            return false;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File\n" + e);
            return false;
        }

        for (int i = 0; i < agents.size(); i++) {
            if (agents.get(i).toString().contains(username) && agents.get(i).toString().contains(password))
                return true;
        }
        return false;
    }

    public void handleSystemExitBtn(ActionEvent e) throws Exception {
        System.exit(0);
    }

    public void handleRegisterAgentBtn(ActionEvent e) throws Exception {
        Main.set_pane(6);
    }

    public void handleLogOutBtn(ActionEvent e) throws Exception {
        Main.set_pane(0);
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(0);
    }
}
