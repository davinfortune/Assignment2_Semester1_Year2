package ApplicationModels;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    int id1 = 123;
    int id2 = 123456;
    Property property1 = new Property(id1, "1 Bedroom", "9 Ard na Groi", "Apartment", "Co.Waterford", "Tramore", "123DD"
            , "X91X4F3", 123412.0, "/img/Test.png");
    Property property2 = new Property(id2, "2 Bedroom", "10 Ard na Groi", "Industrial", "Co.Waterford", "Tramore", "123DY"
            , "X91X4F4", 250000.0, "/img/Test.png");

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        property1 = property2 = null;
    }

    @Test
    public void mainTests(){
        // ID Tests.. Testing for future validation
        assertTrue(property1.getPropertyId() == id1);
        if(property1.getPropertyId() != id1) {
            property2.setPropertyId(id1);
        }
        else {
            assertTrue(property2.getPropertyId() == id2);
        }
        //Getter works Correct for ID
    }
}