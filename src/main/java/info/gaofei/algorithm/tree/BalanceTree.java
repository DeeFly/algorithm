package info.gaofei.algorithm.tree;

/**
 * Created by GaoQingming on 2018/10/1.
 */
public class BalanceTree<AnyType extends Comparable> {
    Node<AnyType> root;

    public void midOrder() {
        midOrder(root);
    }

    private void midOrder(Node<AnyType> node) {
        if (node != null) {
            midOrder(node.left);
            System.out.println(node.value);
            midOrder(node.right);
        }
    }

    public void add(AnyType value) {
        root = add(value, root);
    }

    private Node<AnyType> add(AnyType value, Node<AnyType> node) {
        if (node == null) {
            return new Node<AnyType>(value);
        }

        int compareResult = value.compareTo(node.value);

        if (compareResult < 0) {
            node.left = add(value, node.left);
        } else if (compareResult > 0) {
            node.right = add(value, node.right);
        } else {
            //重复值，不做修改
        }

        return balance(node);
    }

    public void remove(AnyType value) {
        remove(value, root);
    }

    private Node<AnyType> remove(AnyType value, Node<AnyType> node) {
        if (node == null) {
            return node;
        }

        int compareResult = node.value.compareTo(value);
        if (compareResult > 0) {
            node.left = remove(value, node.left);
        } else if (compareResult < 0) {
            node.right = remove(value, node.right);
        } else {//node 的元素和要删除的元素值相等
            if (node.left != null && node.right != null) { //左右子节点都不为null，则用右节点的最小值覆盖要删除的值，然后删除右节点最小值的node即可
                node.value = findMin(node.right);
                node.right = remove(node.value, node.right);
            } else { //左右子节点不全存在，如果有非空子节点，则覆盖自己，如果左右都为null，则将自己设置为null
                node = node.left == null ? node.right : node.left;
            }
        }

        return balance(node);
    }

    private AnyType findMin(Node<AnyType> node) {
        if (node.left != null) {
            return findMin(node.left);
        }
        return node.value;
    }

    private Node<AnyType> balance(Node<AnyType> node) {

        if (node == null) {
            return node;
        }

        if (height(node.left) - height(node.right) > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateWithLeftChild(node);
            } else {
                node = doubleRotateWithLeftChild(node);
            }
        } else if (height(node.right) - height(node.left) > 1) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateWithRightChild(node);
            } else {
                node = doubleRotateWithRightChild(node);
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private Node<AnyType> doubleRotateWithRightChild(Node<AnyType> node) {
        node.right = rotateWithLeftChild(node.right);
        return rotateWithRightChild(node);
    }

    private Node<AnyType> rotateWithRightChild(Node<AnyType> node) {
        Node<AnyType> result = node.right;
        node.right = result.left;
        result.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        result.height = Math.max(height(result.right), height(node)) + 1;
        return result;
    }

    private Node<AnyType> doubleRotateWithLeftChild(Node<AnyType> node) {
        node.left = rotateWithRightChild(node.left);
        return rotateWithLeftChild(node);
        //return node;
    }

    private Node<AnyType> rotateWithLeftChild(Node<AnyType> node) {
        Node<AnyType> result = node.left;
        node.left = result.right;
        result.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        result.height = Math.max(height(result.left), height(result.right)) + 1;
        return result;
    }

    private int height(Node<AnyType> node) {
        return node == null ? -1 : node.height;
    }

    class Node<AnyType>{
        Node(AnyType value) {
            this(value, null, null);
        }

        Node(AnyType value, Node<AnyType> left, Node<AnyType> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
        AnyType value;
        Node<AnyType> left;
        Node<AnyType> right;
        int height;
    }
}
