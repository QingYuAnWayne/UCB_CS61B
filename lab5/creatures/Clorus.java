package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implement of a fierce blue-colored predator Clocurs.
 *
 * @author Qing YuAn
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * Create a clorus with energy equal to e.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * Create a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }


    /**
     * Cloruses should lose 0.03 units of energy on a MOVE action.
     */
    @Override
    public void move() {
        energy -= 0.03;
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature’s energy.
     */
    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * when a Clorus replicates, it keeps 50% of its energy.
     * The other 50% goes to its offspring. No energy is lost in the replication process.
     * @return Baby clorus
     */
    @Override
    public Clorus replicate() {
        Clorus baby = new Clorus(energy * 0.5);
        energy *= 0.5;
        return baby;
    }

    /**
     * Cloruses should lose 0.01 units of energy on a STAY action.
     */
    @Override
    public void stay() {
        energy -= 0.01;
    }

    /**
     * If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
     * Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * Otherwise, the Clorus will MOVE to a random empty square.
     * @param neighbors neignbors
     * @return The action
     */
    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipsNeighbors = new ArrayDeque<>();

        // Rule 1
        for (Direction d: neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.add(d);
            } else if (neighbors.get(d).name().equals("plip")) {
                plipsNeighbors.add(d);
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (!plipsNeighbors.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, HugLifeUtils.randomEntry(plipsNeighbors));
        }

        // Rule 3
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        }
    }

    /**
     * Should always return the color red = 34, green = 0, blue = 231.
     */
    @Override
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
}
