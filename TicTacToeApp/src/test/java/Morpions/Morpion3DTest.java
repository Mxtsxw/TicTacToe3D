package Morpions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Morpion3DTest {
    private Morpion3D morpion;

    @BeforeEach
    public void setUp() {
        morpion = new Morpion3D(3);
    }

    @Test
    public void testInitialization() {
        morpion.init();
        for (int i = 0; i < morpion.getTaille(); i++) {
            for (int j = 0; j < morpion.getTab()[i].length; j++) {
                assertEquals("0", morpion.getTab()[i][j]);
            }
        }
    }

    @Test
    public void testIsFull(){
        morpion.init();
        for (int i = 0; i < morpion.getTaille(); i++) {
            Arrays.fill(morpion.getTab()[i], "Fill");
        }
        assertTrue(morpion.isFull());
    }

    @Test
    public void testIsFullFalse(){
        morpion.init();
        for (int i = 0; i < morpion.getTaille(); i++) {
            Arrays.fill(morpion.getTab()[i], "Fill");
        }
        morpion.getTab()[0][2] = "0";
        assertFalse(morpion.isFull());
    }

    @Test
    public void testPlacer() {
        morpion.init();

        morpion.placer(11, "X");
        morpion.placer(22, "O");

        assertEquals("X", morpion.getTab()[0][0]);
        assertEquals("O", morpion.getTab()[1][1]);
    }

    @Test
    public void testPlacerExceptionInvalidIndex() {
        morpion.init();
        assertThrows(IllegalArgumentException.class, () -> morpion.placer(14, "X"));
        assertThrows(IllegalArgumentException.class, () -> morpion.placer(1, "X"));
        assertThrows(IllegalArgumentException.class, () -> morpion.placer(012, "X"));
    }

    @Test
    public void testCheckWin(){
        morpion.init();
        morpion.placer(11, "X");
        morpion.placer(21, "X");
        morpion.placer(31, "X");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(12, "X");
        morpion.placer(22, "X");
        morpion.placer(32, "X");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(13, "X");
        morpion.placer(23, "X");
        morpion.placer(33, "X");
        assertTrue(morpion.checkWin());

//        morpion.init();
//        morpion.placer(13, "X");
//        morpion.placer(43, "X");
//        morpion.placer(73, "X");
//        morpion.afficher();
//        assertTrue(morpion.checkWin());


    }
}