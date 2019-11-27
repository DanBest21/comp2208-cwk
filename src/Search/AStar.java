package Search;

import Tree.Node;

import java.util.PriorityQueue;
import java.util.Queue;

// Class that implements the A* Heuristic Search algorithm by using a PriorityQueue that prioritises nodes by their heuristic.
public class AStar extends Search
{
    private final Queue<Node> fringe = new PriorityQueue<>();

    public AStar(boolean debugMode)
    {
        super(debugMode);
    }

    public Node search()
    {
        Node node = new Node(startState, null);

        // Get the estimated cost of the node - the Manhattan distance (heuristic) between the grid state and that of the solution.
        int estimatedCost = node.getValue().calculateManhattanDistance(solutionState);
        node.setEstimatedCost(estimatedCost);

        fringe.add(node);

        while (!fringe.isEmpty())
        {
            node = fringe.remove();

            // Return the node if it is the solution state.
            if (node.getValue().equals(solutionState))
                return returnSolution(node);

            expandNode(node);

            int i = 1;

            if (debugMode)
            {
                System.out.println("===== A* Heuristic Search information =====\n");
                System.out.println("Depth at " + (node.getDepth() + 1) + "\n");
            }

            // For each expanded child node, calculate the estimated cost using the current depth and Manhattan distance (heuristic), and put it into the PriorityQueue.
            for (Node childNode : node.getChildren())
            {
                int heuristic = childNode.getValue().calculateManhattanDistance(solutionState);
                estimatedCost = childNode.getDepth() + heuristic;

                if (debugMode)
                {
                    System.out.println("Child " + i + ":");
                    System.out.println("Heuristic (Manhattan distance): " + heuristic);
                    System.out.println("Evaluation function value: " + estimatedCost + "\n");
                }

                childNode.setEstimatedCost(estimatedCost);

                fringe.add(childNode);

                i++;
            }
        }

        return null;
    }
}