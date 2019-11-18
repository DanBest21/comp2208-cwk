import java.util.LinkedList;
import java.util.Queue;

public class BFS extends Search
{
    private Queue<Node> queue = new LinkedList<>();

    public BFS()
    {
        super();
    }

    public Node search()
    {
        Node node = new Node(startState, null);

        queue.add(node);

        while (!queue.isEmpty())
        {
            node = queue.remove();

            if (node.getValue().equals(solutionState))
                return node;

            expandNodes(node);

            queue.addAll(node.getChildren());
        }

        return null;
    }
}