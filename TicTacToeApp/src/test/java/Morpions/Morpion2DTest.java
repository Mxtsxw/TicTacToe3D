package Morpions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Morpion2DTest {
    private Morpion2D morpion;

    @BeforeEach
    public void setUp() {
        morpion = new Morpion2D(3);
    }

    @Test
    public void testInitialization() {
        morpion.init();
        for (int i = 0; i < morpion.getTab().length; i++) {
            assertEquals("0", morpion.getTab()[i]);
        }
    }

    @Test
    public void testPlacer() {
        morpion.init();

        morpion.placer(1, "X");
        morpion.placer(2, "O");

        assertEquals("X", morpion.getTab()[0]);
        assertEquals("O", morpion.getTab()[1]);
    }

    @Test
    public void testPlacerExceptionInvalidIndex() {
        Morpion2D m = new Morpion2D(3);
        assertThrows(IllegalArgumentException.class, () -> m.placer(0, "X"));
    }

    @Test
    public void testPlacerExceptionExistingIndex() {
        morpion.init();
        morpion.placer(1, "X");
        assertEquals("X", morpion.getTab()[0]);
        assertThrows(IllegalArgumentException.class, () -> morpion.placer(1, "X"));
    }

    @Test
    public void testCheckWin() {

        // Test horizontal win
        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(2, "X");
        morpion.placer(3, "X");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(4, "O");
        morpion.placer(5, "O");
        morpion.placer(6, "O");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(7, "X");
        morpion.placer(8, "X");
        morpion.placer(9, "X");
        assertTrue(morpion.checkWin());

        // Test vertical win
        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(4, "X");
        morpion.placer(7, "X");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(2, "X");
        morpion.placer(5, "X");
        morpion.placer(8, "X");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(3, "X");
        morpion.placer(6, "X");
        morpion.placer(9, "X");
        assertTrue(morpion.checkWin());

        // Test diagonal win
        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(5, "X");
        morpion.placer(9, "X");
        assertTrue(morpion.checkWin());

        morpion.init();
        morpion.placer(3, "X");
        morpion.placer(5, "X");
        morpion.placer(7, "X");
        assertTrue(morpion.checkWin());

        // Test no win
        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(2, "O");
        morpion.placer(3, "X");
        assertFalse(morpion.checkWin());
    }

    @Test
    public void testIsFullTrue(){

        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(2, "O");
        morpion.placer(3, "X");
        morpion.placer(4, "X");
        morpion.placer(5, "O");
        morpion.placer(6, "X");
        morpion.placer(7, "O");
        morpion.placer(8, "X");
        morpion.placer(9, "O");

        assertFalse(morpion.checkWin());
        assertTrue(morpion.isFull());

    }

    @Test
    public void testIsFullFalse(){

        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(2, "O");
        morpion.placer(3, "X");
        morpion.placer(4, "X");
        morpion.placer(5, "0");
        morpion.placer(6, "X");
        morpion.placer(7, "O");
        morpion.placer(8, "X");
        morpion.placer(9, "O");

        assertFalse(morpion.checkWin());
        assertFalse(morpion.isFull());

    }

    @Test
    public void testAlignement(){
        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(2, "X");
        morpion.placer(3, "X");
        int[] expected = new int[]{0, 1, 2};
        assertArrayEquals(expected, this.morpion.alignement());

        morpion.init();
        morpion.placer(4, "O");
        morpion.placer(5, "O");
        morpion.placer(6, "O");
        expected = new int[]{3, 4, 5};
        assertArrayEquals(expected, this.morpion.alignement());

        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(5, "X");
        morpion.placer(9, "X");
        expected = new int[]{0, 4, 8};
        assertArrayEquals(expected, this.morpion.alignement());

        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(4, "X");
        morpion.placer(7, "X");
        expected = new int[]{0, 3, 6};
        assertArrayEquals(expected, this.morpion.alignement());

        morpion.init();
        morpion.placer(1, "X");
        morpion.placer(4, "O");
        morpion.placer(7, "X");
        expected = new int[]{};
        assertArrayEquals(expected, this.morpion.alignement());
    }
}

