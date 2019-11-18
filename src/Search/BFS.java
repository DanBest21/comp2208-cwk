package Search;

import java.util.LinkedList;
import java.util.Queue;
import Tree.Node;

public class BFS extends Search
{
    private Queue<Node> fringe = new LinkedList<>();

    public BFS()
    {
        super();
    }

    public Node search()
    {
        Node node = new Node(startState, null);

        fringe.add(node);

        while (!fringe.isEmpty())
        {
            node = fringe.remove();

            if (node.getValue().equals(solutionState))
                return node;

            expandNodes(node);

            fringe.addAll(node.getChildren());
        }

        return null;
    }
}