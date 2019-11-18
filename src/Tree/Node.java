package Tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
        private final Grid value;
        private final Node parent;
        private final List<Node> children;

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

        public void addChild(Node child)
        {
            this.children.add(child);
        }
}