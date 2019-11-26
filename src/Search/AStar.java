package Search;

import Tree.Node;

import java.util.PriorityQueue;
import java.util.Queue;

// Class that implements the A* Heuristic Search algorithm by using a PriorityQueue that prioritises nodes by their heuristic.
public class AStar extends Search
{
    private final Queue<Node> fringe = new PriorityQueue<>();

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

            // For each expanded child node, calculate the estimated cost using the current depth and Manhattan distance (heuristic), and put it into the PriorityQueue.
            for (Node childNode : node.getChildren())
            {
                estimatedCost = childNode.getDepth() + childNode.getValue().calculateManhattanDistance(solutionState);

                childNode.setEstimatedCost(estimatedCost);

                fringe.add(childNode);
            }
        }

        return null;
    }
}