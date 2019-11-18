package Search;

import Tree.Node;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFS extends Search
{
    private Stack<Node> fringe = new Stack<>();

    public DFS()
    {
        super();
    }

    public Node search()
    {
        Node node = new Node(startState, null);

        fringe.push(node);

        while (!fringe.isEmpty())
        {
            node = fringe.pop();

            if (node.getValue().equals(solutionState))
                return node;

            expandNodes(node);

            List<Node> children = node.getChildren();
            Collections.shuffle(children);

            fringe.addAll(children);
        }

        return null;
    }
}