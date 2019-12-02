package Application;


import ApplicationModels.LinkListObjects;
import ApplicationModels.propertyAgent;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;

public class AgentController {
    LinkListObjects agents = new LinkListObjects();

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextArea txtAreaFeedback;
    
    public void handleLoginBtn(ActionEvent e) throws Exception {
        if(txtUsername.getText().length()<4 || txtPassword.getText().length()<4 ){
            txtAreaFeedback.setText("Username and Password need \nto be 4 characters or more");
        }
        else if(login(txtUsername.getText(),txtPassword.getText())){
            txtAreaFeedback.setText("Successful Login");
            Main.set_pane(7);
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
            txtAreaFeedback.setText("Password File not located\n" + e);
            return false;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File\n" + e);
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
        Main.set_pane(0);
    }
}
