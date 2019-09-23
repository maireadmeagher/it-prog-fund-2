import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    private Library library, populatedLibrary;
    private DVD dvd1, dvd2, dvd3;
    private ArrayList<DVD> emptyDVDs, populatedDVDs;

    @BeforeEach
    void setUp() {
        //A Library object that will be empty at the beginning of each test
        library = new Library();

        //An empty ArrayList of DVDs created independently of the Library class.
        //This will be used to compare with the ArrayList created in Library.
        emptyDVDs = new ArrayList<DVD>();

        //A populated ArrayList of DVDs created independently of the Library class.
        //This will be used to compare with the ArrayList created in Library.
        populatedDVDs = new ArrayList<DVD>();
        dvd1 = new DVD("The Hobbit(Director)");  //title with 20 characters
        dvd2 = new DVD("The Steve Jobs Film");   //title with 19 characters
        dvd3 = new DVD("Avatar: Directors Cut"); //title with 21 characters
        populatedDVDs.add(dvd1);
        populatedDVDs.add(dvd2);
        populatedDVDs.add(dvd3);

        //A Library object that will be populated with three DVDs at the beginning of each test
        populatedLibrary = new Library();
        populatedLibrary.setDVDs(populatedDVDs);
    }

    @AfterEach
    void tearDown() {
        library = populatedLibrary = null;
        dvd1 = dvd2 = dvd3 = null;
        populatedDVDs = emptyDVDs = null;
    }

    @Test
    void add() {
        //Testing the ArrayList is Empty
        assertEquals(0, library.getDVDs().size());

        //Testing the adding of the first dvd and making sure the title
        //was setup correctly.
        library.add(new DVD("The Avengers"));
        assertEquals(1, library.getDVDs().size());
        assertEquals("The Avengers", library.getDVDs().get(0).getTitle());

        //Testing the adding of the second dvd
        library.add(new DVD("Peppa Pig"));
        assertEquals(2, library.getDVDs().size());
        assertEquals("Peppa Pig", library.getDVDs().get(1).getTitle());
    }

    @Test
    void getDVDs() {
        //The new library object size is zero
        assertEquals(0, library.getDVDs().size());
        //The new library object returns an empty ArrayList of DVDs
        assertEquals(emptyDVDs, library.getDVDs());

        //The populated library object size is three DVDs
        assertEquals(3, populatedLibrary.getDVDs().size());
        //The populated library object returns an ArrayList with three DVDs
        assertEquals(populatedDVDs, populatedLibrary.getDVDs());
    }

    @Test
    void setDVDs() {
        //The new library object size is zero.  Set the DVDs
        //ArrayList to an empty list; zero should still be returned
        assertEquals(0, library.getDVDs().size());
        library.setDVDs(emptyDVDs);
        assertEquals(0, library.getDVDs().size());

        //Now set the DVDs ArrayList with the populatedDVDs.
        //3 should still be returned
        library.setDVDs(populatedDVDs);
        assertEquals(3, library.getDVDs().size());
        //The contents of the library DVDs should also be the
        //same as the populatedDVDs.
        assertEquals(populatedDVDs, library.getDVDs());
    }

    @Test
    void listDVDs() {
        //The new library object returns an empty String
        assertEquals("No DVDs.", library.listDVDs());

        //The populated library object returns an String listing three DVDs
        assertEquals("0:DVD Title is: The Hobbit(Director)\n"
                        + "1:DVD Title is: The Steve Jobs Film\n"
                        + "2:DVD Title is: Avatar: Directors Cu\n",
                populatedLibrary.listDVDs());
    }


    @Test
    public void testSaveAndLoad() throws Exception {
        //TESTING AN EMPTY ARRAYLIST
        //--------------------------
        //Saving a new library object with an empty ArrayList of DVD
        assertEquals(0, library.getDVDs().size());
        assertEquals(emptyDVDs, library.getDVDs());
        library.save();
        //Load the file into another library object and compare it to emptyDVDs
        Library library2 = new Library();
        library2.load();
        assertEquals(library2.getDVDs().size(), library.getDVDs().size());

        //TESTING A POPULATED ARRAYLIST
        //-----------------------------
        //Saving a library object with a populated ArrayList of DVD
        assertEquals(3, populatedLibrary.getDVDs().size());
        assertEquals(populatedDVDs, populatedLibrary.getDVDs());
        populatedLibrary.save();
        //Load the file into another library object and compare it to populatedLibrary
        Library library3 = new Library();
        library3.load();
        assertEquals(library3.getDVDs().size(), populatedLibrary.getDVDs().size());
        assertEquals(library3.getDVDs().get(1).getTitle(), populatedLibrary.getDVDs().get(1).getTitle());
        assertEquals(library3.getDVDs().get(2).getTitle(), populatedLibrary.getDVDs().get(2).getTitle());
    }

}