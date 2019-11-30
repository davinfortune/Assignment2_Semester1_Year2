package MethodModels;

import ApplicationModels.LinkListObjects;
import ApplicationModels.propertyAgent;
import ApplicationModels.propertyAgent;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class agentModel {
    public static LinkListObjects agents = new LinkListObjects();

    public static void loadAgent() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream
                (new FileReader("saveFiles/agents.xml"));
        agents = (LinkListObjects) is.readObject();
        is.close();
    }

    public static void saveAgent(propertyAgent agentLocal) throws Exception {
        XStream xstream = new XStream(new DomDriver());
        agents.add(agentLocal);
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("saveFiles/agents.xml"));
        out.writeObject(agents);
        out.close();
    }

}
