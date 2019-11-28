package Tree;

import java.util.ArrayList;
import java.util.List;

// Class that defines a Node, which is used to form a Tree structure by means of the parent and children variables.
public class Node implements Comparable<Node>
{
        private final Grid value;
        private final Node parent;
        private final List<Node> children;
        private double estimatedCost;

        public Node(Grid value, Node parent)
        {
            this.value = value;
            this.parent = parent;
            this.children = new ArrayList<>();
        }

        public Grid getValue()
        {
            return value;
        }

        public Node getParent()
        {
            return parent;
        }

        public List<Node> getChildren()
        {
            return children;
        }

        // Method that returns the depth of the Node in the tree.
        public int getDepth()
        {
            int depth = 0;
            Node node = this;

            while (node.parent != null)
            {
                depth++;
                node = node.parent;
            }

            return depth;
        }

        public void setEstimatedCost(double estimatedCost)
        {
            this.estimatedCost = estimatedCost;
        }

        public double getEstimatedCost()
        {
            return estimatedCost;
        }

        public void addChild(Node child)
        {
            this.children.add(child);
        }

        // compareTo() method that defines how Nodes work as a Comparable, which is necessary for use in a PriorityQueue.
        public int compareTo(Node node)
        {
            return Double.compare(this.getEstimatedCost(), node.getEstimatedCost());
        }
}