package Search;

import java.util.LinkedList;
import java.util.Queue;
import Tree.Node;

// Class that implements the Breadth First Search (BFS) algorithm by using a Queue to manage the fringe.
public class BFS extends Search
{
    private final Queue<Node> fringe = new LinkedList<>();

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

            // Return the node if it is the solution state.
            if (node.getValue().equals(solutionState))
                return returnSolution(node);

            expandNode(node);

            // Add all the children to the queue, so they 'join the back of the queue'.
            fringe.addAll(node.getChildren());
        }

        return null;
    }
}