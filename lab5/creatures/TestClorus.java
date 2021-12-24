package creatures;

import huglife.*;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.HashMap;

public class TestClorus {

    @Test
    public void testAttack() {
        Clorus c = new Clorus(2);
        c.attack(new Plip(2));

        assertEquals(4, c.energy(), 0.01);

        c.attack(new Plip(0));
        assertEquals(4, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus cSon = c.replicate();

        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, cSon.energy(), 0.01);

        Clorus cPoor = new Clorus(0);
        Clorus cPoorSon = cPoor.replicate();

        assertEquals(0, cPoor.energy(), 0.01);
        assertEquals(0, cPoorSon.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // No empty adjacent spaces, even if there are plips nearby; stay.
        c = new Clorus(1);
        HashMap<Direction, Occupant> surroundedPlips = new HashMap<>();
        surroundedPlips.put(Direction.TOP, new Plip(1));
        surroundedPlips.put(Direction.BOTTOM, new Plip(1));
        surroundedPlips.put(Direction.LEFT, new Plip(1));
        surroundedPlips.put(Direction.RIGHT, new Plip(1));

        actual = c.chooseAction(surroundedPlips);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // There are empty adjacent spaces and plip nearby; attack.
        c = new Clorus(1);
        HashMap<Direction, Occupant> surroundedPlipsAndEmptySpaces = new HashMap<>();
        surroundedPlipsAndEmptySpaces.put(Direction.TOP, new Plip(1));
        surroundedPlipsAndEmptySpaces.put(Direction.BOTTOM, new Empty());
        surroundedPlipsAndEmptySpaces.put(Direction.LEFT, new Empty());
        surroundedPlipsAndEmptySpaces.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(surroundedPlipsAndEmptySpaces);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1; replicate.
        c = new Clorus(1);
        HashMap<Direction, Occupant> surroundedEmptySpaces = new HashMap<>();
        surroundedEmptySpaces.put(Direction.TOP, new Empty());
        surroundedEmptySpaces.put(Direction.BOTTOM, new Impassible());
        surroundedEmptySpaces.put(Direction.LEFT, new Impassible());
        surroundedEmptySpaces.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(surroundedEmptySpaces);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        // Energy >= 1; replicate.
        c = new Clorus(1);
        surroundedEmptySpaces = new HashMap<>();
        surroundedEmptySpaces.put(Direction.TOP, new Empty());
        surroundedEmptySpaces.put(Direction.BOTTOM, new Empty());
        surroundedEmptySpaces.put(Direction.LEFT, new Empty());
        surroundedEmptySpaces.put(Direction.RIGHT, new Empty());
        actual = c.chooseAction(surroundedEmptySpaces);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        // Energy < 1; move.
        c = new Clorus(.99);
        actual = c.chooseAction(surroundedEmptySpaces);
        unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);

        // Energy < 1; move.
        c = new Clorus(.99);
        surroundedEmptySpaces = new HashMap<>();
        surroundedEmptySpaces.put(Direction.TOP, new Empty());
        surroundedEmptySpaces.put(Direction.BOTTOM, new Impassible());
        surroundedEmptySpaces.put(Direction.LEFT, new Impassible());
        surroundedEmptySpaces.put(Direction.RIGHT, new Impassible());
        actual = c.chooseAction(surroundedEmptySpaces);
        expected = new Action(Action.ActionType.MOVE, Direction.TOP);

        assertEquals(expected, actual);
    }
}
