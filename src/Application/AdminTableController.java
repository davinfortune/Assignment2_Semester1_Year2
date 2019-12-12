package Application;

import ApplicationModels.LinkListObjects;
import ApplicationModels.propertyAgent;
import ApplicationModels.propertyAgent;

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
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminTableController implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtAgentID;
    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private TextField txtLocation;
    @FXML
    private TextArea txtFeedBack;
    @FXML
    private TableView tableViewAdmin;
    @FXML
    private TableColumn<propertyAgent, String> usernameColumn;
    @FXML
    private TableColumn<propertyAgent, String> agentIDColumn;
    @FXML
    private TableColumn<propertyAgent, String> fullNameColumn;
    @FXML
    private TableColumn<propertyAgent, String> locationColumn;
    @FXML
    private TableColumn<propertyAgent, Integer> phoneNumberColumn;
    
    private int index;


    public void handleUpdateAgentBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/updateAgent.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        AdminTableController controller = loader.getController();
        propertyAgent propertyAgentToDisplay =(propertyAgent)tableViewAdmin.getSelectionModel().getSelectedItem();


        LinkListObjects temp = new LinkListObjects();
        ArrayList<propertyAgent> tempArray = new ArrayList<>();
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/agents.xml"));
        temp = (LinkListObjects) is.readObject();
        is.close();
        for (int i = 0; i < temp.size(); i++) {
            propertyAgent forPropertyAgent = (propertyAgent) temp.get(i);
            tempArray.add(forPropertyAgent);
            if(((propertyAgent) temp.get(i)).getUsername() == propertyAgentToDisplay.getUsername()){
                index = i;
            }
        }
        if(propertyAgentToDisplay == null) {
            return;
        }
        else

        controller.initData(propertyAgentToDisplay);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleUpdateAgent(ActionEvent e) throws Exception{
        LinkListObjects temp = new LinkListObjects();
        LinkListObjects tempSave = new LinkListObjects();
        ArrayList<propertyAgent> tempArray = new ArrayList<>();
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/agents.xml"));
        temp = (LinkListObjects) is.readObject();
        is.close();
        for (int i = 0; i < temp.size(); i++) {
            propertyAgent forpropertyAgent = (propertyAgent) temp.get(i);
            tempArray.add(forpropertyAgent);
        }
        tempArray.get(index).setAgentId(txtAgentID.getText());
        tempArray.get(index).setUsername(txtUsername.getText());
        tempArray.get(index).setPassword(txtPassword.getText());
        tempArray.get(index).setName(txtFullName.getText());
        tempArray.get(index).setLocation(txtLocation.getText());
        tempArray.get(index).setPhoneNumber(Integer.parseInt(txtPhoneNumber.getText()));

        for (int i = 0; i < temp.size(); i++) {
            tempSave.add(tempArray.get(i));
        }
        XStream save = new XStream(new DomDriver());
        ObjectOutputStream out = save.createObjectOutputStream(new FileWriter("saveFiles/agents.xml"));
        out.writeObject(tempSave);
        out.close();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenAdmin.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    
    

    public void initData(propertyAgent agent)
    {
          txtUsername.setText(""+agent.getUsername());
          txtPassword.setText(""+agent.getPassword());
          txtAgentID.setText(""+agent.getAgentId());
          txtFullName.setText(""+agent.getName());
          txtPhoneNumber.setText(""+agent.getPhoneNumber());
          txtLocation.setText(""+agent.getLocation());

    }


    public void handleDeleteBtn(ActionEvent e) throws Exception {
        int d=0;
        propertyAgent agentToDisplay = (propertyAgent) tableViewAdmin.getSelectionModel().getSelectedItem();
        LinkListObjects temp = new LinkListObjects();
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/agents.xml"));
        temp = (LinkListObjects) is.readObject();
        is.close();
        for (int i = 0; i < temp.size(); i++) {
            if (((propertyAgent) temp.get(i)).getAgentId() == agentToDisplay.getAgentId()) {
                d = i;
            }
        }
        try {
            temp.remove(d);
            XStream save = new XStream(new DomDriver());
            ObjectOutputStream out = save.createObjectOutputStream(new FileWriter("saveFiles/agents.xml"));
            out.writeObject(temp);
            out.close();
        }
        catch (Exception z){
            txtFeedBack.setText("Can not remove propertyAgent\n" + e);
        }

        try {
            LinkListObjects tempAgents;
            ArrayList<propertyAgent> tableAgent = new ArrayList<>();
            XStream reset = new XStream(new DomDriver());
            ObjectInputStream set = reset.createObjectInputStream
                    (new FileReader("saveFiles/agents.xml"));
            tempAgents = (LinkListObjects) set.readObject();
            set.close();
            for (int i = 0; i < tempAgents.size(); i++) {
                propertyAgent forProperty = (propertyAgent) tempAgents.get(i);
                tableAgent.add(forProperty);
            }

            ObservableList<propertyAgent> data = FXCollections.observableArrayList(tableAgent);

            usernameColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("username"));
            agentIDColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("agentId"));
            fullNameColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("name"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, Integer>("phoneNumber"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("location"));

            tableViewAdmin.setItems(data);
        } catch (Exception f) {
            txtFeedBack.setText("Could Not Load Agents");
        }
    }
    
    public void handleLogOutBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenGeneral.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleAddAgentBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/registerAgent.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleReturnHomeBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/FXML/homeScreenAdmin.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    @Override

    public void initialize(URL location, ResourceBundle resources) {
        try {
            LinkListObjects tempAgents;
            ArrayList<propertyAgent> tableAgents = new ArrayList<>();
            XStream xstream = new XStream(new DomDriver());
            ObjectInputStream is = xstream.createObjectInputStream
                    (new FileReader("saveFiles/agents.xml"));
            tempAgents = (LinkListObjects) is.readObject();
            is.close();
            for (int i = 0; i < tempAgents.size(); i++) {
                propertyAgent forAgents = (propertyAgent) tempAgents.get(i);
                tableAgents.add(forAgents);
            }

            ObservableList<propertyAgent> data = FXCollections.observableArrayList(tableAgents);


            usernameColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("username"));
            agentIDColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("agentId"));
            fullNameColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("name"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, Integer>("phoneNumber"));
            locationColumn.setCellValueFactory(new PropertyValueFactory<propertyAgent, String>("location"));

            tableViewAdmin.setItems(data);
        } catch (Exception e) {
            txtFeedBack.setText("Could Not Load Agents" + e);
        }
    }
}
