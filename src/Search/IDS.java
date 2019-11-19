package Search;

import Tree.Node;

import java.util.List;
import java.util.Stack;

public class IDS extends Search
{
    private final Stack<Node> fringe = new Stack<>();

    public Node search()
    {
        Node solution = null;
        int i = 1;

        while (solution == null)
        {
            solution = depthLimitedSearch(i);
            i++;
        }

        return solution;
    }

    private Node depthLimitedSearch(int limit)
    {
        Node node = new Node(startState, null, nodesVisited);

        fringe.push(node);

        while (!fringe.isEmpty())
        {
            node = fringe.pop();
            nodesVisited++;

            if (node.getValue().equals(solutionState))
                return node;

            if (node.getDepth() == limit)
                continue;

            expandNodes(node, nodesVisited);

            List<Node> children = node.getChildren();

            fringe.addAll(children);
        }

        return null;
    }
}