package Search;

import Tree.Node;

import java.util.List;
import java.util.Stack;

// Class that implements the Iterative Deepening Search (IDS) algorithm by using a Depth Limited Search (DLS), a modified version of DFS that stops at a certain limit, and then iterates this algorithm until a solution is found.
public class IDS extends Search
{
    private final Stack<Node> fringe = new Stack<>();

    public Node search()
    {
        Node solution = null;
        int i = 1;

        // While the solution has not been returned by DLS, increase the limit by 1.
        while (solution == null)
        {
            solution = depthLimitedSearch(i);
            i++;
        }

        return solution;
    }

    private Node depthLimitedSearch(int limit)
    {
        Node node = new Node(startState, null);

        fringe.push(node);

        while (!fringe.isEmpty())
        {
            node = fringe.pop();

            // Return the node if it is the solution state.
            if (node.getValue().equals(solutionState))
                return node;

            // If the limit has been reached, then do not expand this node.
            if (node.getDepth() == limit)
                continue;

            expandNode(node);

            List<Node> children = node.getChildren();

            // As with DFS, add all of the children to the Stack, so they are the next nodes to be checked.
            fringe.addAll(children);
        }

        return null;
    }
}