package Search;

import Tree.Direction;
import Tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class AStar extends Search
{
    private final Queue<Node> fringe = new PriorityQueue<>();
    private final List<Node> optimalSolution = new ArrayList<>();

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
                checkOptimalSolution(childNode);
            }
        }

        return null;
    }

    // TEMPORARY
    private void checkOptimalSolution(Node node)
    {
        if (optimalSolution.size() == 0 && node.getValue().getLastDirection() == Direction.UP)
            optimalSolution.add(node);
        else if (optimalSolution.size() == 1 && node.getValue().getLastDirection() == Direction.LEFT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 2 && node.getValue().getLastDirection() == Direction.LEFT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 3 && node.getValue().getLastDirection() == Direction.DOWN && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 4 && node.getValue().getLastDirection() == Direction.LEFT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 5 && node.getValue().getLastDirection() == Direction.UP && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 6 && node.getValue().getLastDirection() == Direction.RIGHT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 7 && node.getValue().getLastDirection() == Direction.DOWN && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 8 && node.getValue().getLastDirection() == Direction.RIGHT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 9 && node.getValue().getLastDirection() == Direction.UP && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 10 && node.getValue().getLastDirection() == Direction.UP && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 11 && node.getValue().getLastDirection() == Direction.LEFT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 12 && node.getValue().getLastDirection() == Direction.DOWN && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
        else if (optimalSolution.size() == 13 && node.getValue().getLastDirection() == Direction.LEFT && node.getParent() == optimalSolution.get(optimalSolution.size() - 1))
            optimalSolution.add(node);
    }
}