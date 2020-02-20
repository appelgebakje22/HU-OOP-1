import org.junit.Test;

import src.Practicum2B.Voetbalclub;

import static org.junit.Assert.assertEquals;

public class Practicum3A {
    @Test
    public void leegResultaten() {
        Voetbalclub v1 = new Voetbalclub("");
        Voetbalclub v2 = new Voetbalclub(null);

        assertEquals("FC 0 0 0 0 0", v1.toString());
        assertEquals("FC 0 0 0 0 0", v2.toString());
    }

    @Test
    public void verwerkResultaten() {
        Voetbalclub v1 = new Voetbalclub("Test");

        v1.verwerkResultaat('x');
        assertEquals("Test 0 0 0 0 0", v1.toString());

        v1.verwerkResultaat('w');
        assertEquals("Test 1 1 0 0 3", v1.toString());

        v1.verwerkResultaat('g');
        assertEquals("Test 2 1 1 0 4", v1.toString());

        v1.verwerkResultaat('v');
        assertEquals("Test 3 1 1 1 4", v1.toString());
    }

    @Test
    public void wedstrijdAantalen() {
        Voetbalclub v1 = new Voetbalclub("Test");

        v1.verwerkResultaat('w');
        v1.verwerkResultaat('w');
        v1.verwerkResultaat('g');
        v1.verwerkResultaat('g');
        v1.verwerkResultaat('v');
        v1.verwerkResultaat('v');
        v1.verwerkResultaat('w');
        assertEquals("Test 7 3 2 2 11", v1.toString());

    }
}