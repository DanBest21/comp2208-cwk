package Search;

import Tree.Node;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

// Class that implements the Depth First Search (DFS) algorithm by using a Stack to manage the fringe.
public class DFS extends Search
{
    private final Stack<Node> fringe = new Stack<>();
    private final boolean randomOrder;

    public DFS(boolean randomOrder, boolean debugMode)
    {
        super(debugMode);
        this.randomOrder = randomOrder;
    }

    public Node search()
    {
        Node node = new Node(startState, null);

        fringe.push(node);

        while (!fringe.isEmpty())
        {
            node = fringe.pop();

            // Return the node if it is the solution state.
            if (node.getValue().equals(solutionState))
                return returnSolution(node);

            expandNode(node);

            List<Node> children = node.getChildren();

            // If the order of nodes is supposed to be random, call Collections.shuffle on the children ArrayList.
            if (randomOrder)
                Collections.shuffle(children);

            // Add all of the children to the Stack, so they are the next nodes to be checked.
            fringe.addAll(children);
        }

        return null;
    }
}