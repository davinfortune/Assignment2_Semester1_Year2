package registerControllers;

import ApplicationModels.LinkListObjects;
import Application.Main;
import ApplicationModels.propertyAgent;
import MethodModels.agentModel;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class registerAgentController implements Initializable{

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private TextField txtAgentID;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextArea txtAreaFeedback;
    @FXML
    private ComboBox<String> locationGeneralSelect = new ComboBox<>();

    public void handleAgentRegisterBtn(ActionEvent e) throws Exception {
        int phoneNumber;
        try {
            phoneNumber = Integer.parseInt(txtPhoneNumber.getText());
        }
        catch (Exception t){
            txtAreaFeedback.setText("Cannot use Phone Number");
            phoneNumber = 0;
        }

        if (txtUsername.getText().length() < 4 || txtPassword.getText().length() < 4) {
            txtAreaFeedback.setText("Username and Password need to be 4 characters or more");
        } else if (!txtPassword.getText().equals(txtRepeatPassword.getText())) {
            txtAreaFeedback.setText("Password must match RepeatPassword");
        } else if (addAgent(txtUsername.getText(), txtPassword.getText(), txtAgentID.getText(), locationGeneralSelect.getValue(), txtFullName.getText(), phoneNumber)) {
            Main.set_pane(0);
            txtAreaFeedback.setText("Successful Registration");
        }
    }

    public boolean addAgent(String username, String password, String agentId, String location, String name, int phoneNumber){
        LinkListObjects agents = new LinkListObjects();
        if(agents.isEmpty()) {
            try {
                try{
                    XStream xstream = new XStream(new DomDriver());
                    ObjectInputStream is = xstream.createObjectInputStream
                            (new FileReader("saveFiles/agents.xml"));
                    agents = (LinkListObjects) is.readObject();
                    is.close();
                }
                catch(Exception e){
                    txtAreaFeedback.setText("Cannot Load Agents");
                }

                propertyAgent agentLocal = new propertyAgent(username, password, agentId, location, name, phoneNumber);
                XStream xstream = new XStream(new DomDriver());
                agents.add(agentLocal);
                ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("saveFiles/agents.xml"));
                out.writeObject(agents);
                out.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else {
            try {
                propertyAgent agentLocal = new propertyAgent(username,  password, agentId, location, name, phoneNumber);
                XStream xstream = new XStream(new DomDriver());
                agents.add(agentLocal);
                ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("saveFiles/agents.xml"));
                out.writeObject(agents);
                out.close();
                return true;
            } catch (Exception t) {
                return false;
            }
        }
    }

    ObservableList<String> locations = FXCollections.observableArrayList("Any", "Co.Waterford", "Co.Kilkenny", "Co.Cork", "Co.Limerick");

    @Override

    public void initialize(URL location, ResourceBundle resources) {

        locationGeneralSelect.setItems(locations);

        locationGeneralSelect.setValue("Any");
    }


    public void handleReturnHomeBtn(ActionEvent e) throws Exception
    {
        Main.set_pane(0);
    }

}
