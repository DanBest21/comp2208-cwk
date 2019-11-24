package Search;

import Tree.*;

import java.util.HashMap;
import java.util.Map;

public abstract class Search
{
    private static final int N = 4;

    final Grid startState = new Grid(N);
    final Grid solutionState = new Grid(N);
    int nodesVisited = 1;

    Search()
    {
        solutionState.generateSolutionGrid();
    }

    public void setStartingGrid(int depth)
    {
        startState.generateStartingGrid(depth);
    }

    void expandNodes(Node node, int nodeNumber)
    {
        Grid currentGrid = node.getValue();

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
                Node newNode = new Node(newGrid, node, nodeNumber);
                node.addChild(newNode);
            }
        }
    }

    public abstract Node search();
}


