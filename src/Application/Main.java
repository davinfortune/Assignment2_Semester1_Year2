package Application;


import ApplicationModels.propertyAgent;
import ApplicationModels.propertyAdmin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;


public class  Main extends Application {
    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<AnchorPane>();
    private static int sceneIndex = 0;
    private static propertyAdmin admin = null;
    private static propertyAgent agent = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/Anchor.fxml"));

        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/homeScreenGeneral.fxml"))); //index 0
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/addProperty.fxml"))); //index 1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/registerAgent.fxml"))); //index 2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/registerAdmin.fxml"))); //index 3
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/loginAdmin.fxml"))); //index 4
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/homeScreenAdmin.fxml"))); //index 5
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/loginAgent.fxml")));//index 6
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/homeScreenAgent.fxml")));//index 7
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/viewAllProperty.fxml")));//index 8
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("/FXML/updateProperty.fxml")));//index 9

        primaryStage.setTitle("Davt.ie");
        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.show();
        Main.set_pane(0);
    }

    public static propertyAdmin getAdmin() { return admin; }

    public static propertyAgent getAgent() { return agent; }

    public static void setAdmin(propertyAdmin admin) {Main.admin = admin;}

    public static void setAgent(propertyAgent agent) {Main.agent = agent;}

    private void init_app(){
        for(int i=0; i<anchor.size();i++){
            //can be used to initiate some details about each pane
        }
    }

    public static AnchorPane get_pane(int index){
        return anchor.get(index);
    }

    public static void set_pane(int index){
        root.getChildren().remove(anchor.get(sceneIndex));
        root.getChildren().add(anchor.get(index));
        sceneIndex=index;
    }


    public static void main(String[] args) {
        launch(args);
    }
}

