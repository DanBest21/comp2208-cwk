package Search;

import Tree.*;

import java.util.HashMap;
import java.util.Map;

// Abstract search class that defines a common start and solution state, and provides a common expandNode() method.
public abstract class Search
{
    private static final int N = 4;

    final Grid startState = new Grid(N);
    final Grid solutionState = new Grid(N);
    private int nodesGenerated = 1;
    private int fringeCount = 1;
    final boolean debugMode;

    Search(boolean debugMode)
    {
        this.debugMode = debugMode;
        solutionState.generateSolutionGrid();
    }

    public void setStartingGrid(int depth)
    {
        startState.generateStartingGrid(depth);
    }

    public Grid getStartingGrid()
    {
        return startState;
    }

    public int getNodesGenerated()
    {
        return nodesGenerated;
    }

    // Function that expands the given node by generating every possible successor node, i.e. the directions in which the agent can move.
    void expandNode(Node node)
    {
        Grid currentGrid = node.getValue();

        if (debugMode)
        {
            System.out.println("------------- Fringe " + fringeCount + " ---------------\n");
            System.out.println("Selected node (depth " + node.getDepth() + "):\n" + node.getValue() + "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }

        // For each direction, attempt to move the agent in that direction
        for (Direction dir : Direction.values())
        {
            Map<Character, Integer> blockPositions = new HashMap<>();

            for (Map.Entry entry : currentGrid.getBlockPositions().entrySet())
            {
                char block = (char)entry.getKey();
                int position = (int)entry.getValue();

                blockPositions.put(block, position);
            }

            Grid newGrid = new Grid(currentGrid.getGrid(), currentGrid.getAgentPosition(), blockPositions);

            newGrid.moveAgent(dir);

            if (!newGrid.equals(currentGrid))
            {
                Node newNode = new Node(newGrid, node);
                node.addChild(newNode);

                nodesGenerated++;

                if (debugMode)
                    System.out.println(newNode.getValue());
            }
        }

        fringeCount++;
    }

    Node returnSolution(Node node)
    {
        if (debugMode)
        {
            System.out.println("--------- Solution Node (depth " + node.getDepth() + ") ---------\n");
            System.out.println(node.getValue());
            System.out.println("-------------------------------------------\n");
        }

        return node;
    }

    public abstract Node search();
}


