package Search;

import Tree.Node;

import java.util.PriorityQueue;
import java.util.Queue;

public class AStar extends Search
{
    private final Queue<Node> fringe = new PriorityQueue<>();

    public Node search()
    {
        Node node = new Node(startState, null, nodesVisited);

        int heuristic = node.getValue().calculateManhattanDistance(solutionState);
        node.setHeuristic(heuristic);

        fringe.add(node);

        while (!fringe.isEmpty())
        {
            node = fringe.remove();

            if (node.getValue().equals(solutionState))
            {
                node.setNodeNumber(nodesVisited);
                return node;
            }

            expandNodes(node, nodesVisited);

            for (Node childNode : node.getChildren())
            {
                nodesVisited++;

                heuristic = node.getHeuristic() + childNode.getValue().calculateManhattanDistance(solutionState);
                childNode.setHeuristic(heuristic);

                fringe.add(childNode);
            }
        }

        return null;
    }
}