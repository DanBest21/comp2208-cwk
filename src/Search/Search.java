package Search;

import Tree.*;

public abstract class Search
{
    private static final int N = 4;

    protected final Grid startState = new Grid(N);
    protected final Grid solutionState = new Grid(N);

    protected Search()
    {
        startState.generateStartingGrid();
        solutionState.generateSolutionGrid();
    }

    protected void expandNodes(Node node)
    {
        Grid currentGrid = node.getValue();

        for (Direction dir : Direction.values())
        {
            Grid newGrid = new Grid(currentGrid.getGrid(), currentGrid.getAgentPosition());

            newGrid.moveAgent(dir);

            if (!newGrid.equals(currentGrid))
            {
                Node newNode = new Node(newGrid, node);
                node.addChild(newNode);
            }
        }
    }

    public abstract Node search();
}


