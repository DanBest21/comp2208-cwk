package Search;

import Tree.Node;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFS extends Search
{
    private final Stack<Node> fringe = new Stack<>();
    private final boolean randomOrder;

    public DFS(boolean randomOrder)
    {
        super();
        this.randomOrder = randomOrder;
    }

    public Node search()
    {
        Node node = new Node(startState, null, nodesVisited);

        fringe.push(node);

        while (!fringe.isEmpty())
        {
            node = fringe.pop();
            nodesVisited++;

            if (node.getValue().equals(solutionState))
                return node;

            expandNodes(node, nodesVisited);

            List<Node> children = node.getChildren();

            if (randomOrder)
                Collections.shuffle(children);

            fringe.addAll(children);
        }

        return null;
    }
}