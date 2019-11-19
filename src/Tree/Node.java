package Tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
        private final Grid value;
        private final Node parent;
        private final List<Node> children;
        private final int nodeNumber;

        public Node(Grid value, Node parent, int nodeNumber)
        {
            this.value = value;
            this.parent = parent;
            this.children = new ArrayList<>();
            this.nodeNumber = nodeNumber;
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

        public int getNodeNumber()
        {
            return nodeNumber;
        }

        public int getDepth()
        {
            int depth = 0;
            Node node = this;

            while (node != null)
            {
                depth++;
                node = node.parent;
            }

            return depth;
        }

        public void addChild(Node child)
        {
            this.children.add(child);
        }
}