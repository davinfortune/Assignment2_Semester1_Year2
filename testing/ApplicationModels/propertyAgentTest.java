package ApplicationModels;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class propertyAgentTest {
    String username = "davinfortune";
    String agentID = "1234DD";

    propertyAgent agent = new propertyAgent(username, "1234", agentID, "Co.Waterford", "davin fortune", 830581771);
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        agent = null;
    }

    @Test
    public void mainTest() {
        assertTrue(agent.getUsername().equals(username));
        assertTrue(agent.getAgentId().equals(agentID));
    }


}