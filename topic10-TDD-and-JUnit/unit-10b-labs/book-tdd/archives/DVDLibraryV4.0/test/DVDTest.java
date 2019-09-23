import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DVDTest {

    private DVD dvd1, dvd2, dvd3, dvd4;

    @BeforeEach
    void setUp() {
        dvd1 = new DVD("The Hobbit(Director)");  //title with 20 characters
        dvd2 = new DVD("The Steve Jobs Film");   //title with 19 characters
        dvd3 = new DVD("Avatar: Directors Cut"); //title with 21 characters
        dvd4 = new DVD();
    }

    @AfterEach
    void tearDown() {
        dvd1 = dvd2 = dvd3 = dvd4 = null;
    }

    @Test
    void setTitle() {
        dvd1.setTitle("The Hobbit");
        assertEquals ("The Hobbit", dvd1.getTitle());

        dvd1.setTitle("The Hobbit (Director)");  //attempting to set title to 21 characters
        assertEquals ("The Hobbit (Director", dvd1.getTitle());

        dvd1.setTitle("The Hobbit(Director)");  //attempting to set title to 20 characters
        assertEquals ("The Hobbit(Director)", dvd1.getTitle());

        dvd1.setTitle("The Hobbit:Director");  //attempting to set title to 19 characters
        assertEquals ("The Hobbit:Director", dvd1.getTitle());
    }

    @Test
    void getTitle() {
        assertEquals("The Hobbit(Director)", dvd1.getTitle());
        assertEquals("The Steve Jobs Film", dvd2.getTitle());
        assertEquals("Avatar: Directors Cu", dvd3.getTitle());
        assertEquals(null, dvd4.getTitle());
    }

    @Test
    void testToString() {
        assertEquals("DVD Title is: The Hobbit(Director)", dvd1.toString());
        assertEquals("DVD Title is: The Steve Jobs Film", dvd2.toString());
        assertEquals("DVD Title is: Avatar: Directors Cu", dvd3.toString());
    }
}