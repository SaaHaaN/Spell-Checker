package projectpackage;

public class BinarySearchTree<T extends Comparable<T>> {

    Functions Functions = new Functions();
    Node<T> root;

    //--------------------------------------------------------------------------
    
    public void insert(T newData) {
        Node<T> newNode = new Node<>(newData);
        if (root == null) {
            root = newNode;
        } else {
            Node<T> temp = root;
            while (true) {
                int distance = Functions.levenshteinDistance(newData.toString(), temp.data.toString());
                if (distance > 2) {
                    if (temp.right == null) {
                        temp.right = newNode;
                        return;
                    }
                    temp = temp.right;
                } else {
                    if (temp.left == null) {
                        temp.left = newNode;
                        return;
                    }
                    temp = temp.left;
                }
            }
        }
    }

    //--------------------------------------------------------------------------
    
    boolean search(T key) {
        if (root == null) {
            return false;
        } else {
            Node<T> temp = root;
            while (temp != null) {
                if (key.compareTo(temp.data) > 0) {
                    temp = temp.right;
                } else if (key.compareTo(temp.data) < 0) {
                    temp = temp.left;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    //--------------------------------------------------------------------------
    
    void inorder() {
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<T> node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    //--------------------------------------------------------------------------   
    
    void delete(T key) {
        Node<T> parent = null;
        Node<T> current = root;
        while (current != null && current.data != key) {
            parent = current;
            if (key.compareTo(current.data) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (current == null) {
            return;
        }

        // case 1: leaf node
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else {
                if (current == parent.left) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        } // case 2: has one child
        else if (current.left == null || current.right == null) {
            Node<T> child;
            if (current.left != null) {
                child = current.left;
            } else {
                child = current.right;
            }
            if (current == root) {
                root = null;
            } else {
                if (current == parent.left) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            }
        } // case 2: has two children
        else {
            Node<T> successor = current.right;
            Node<T> successorParent = current;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.data = successor.data;
            successorParent.left = successor.right;
        }
    }

    int size(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }

    //--------------------------------------------------------------------------
    
    StringBuilder allElementsToStringBuilder() { // all elements in tree to string
        StringBuilder stringBuilder = new StringBuilder(); // create string builder
        return allElementsToStringBuilder(root, stringBuilder); // call recursive method
    }

    private StringBuilder allElementsToStringBuilder(Node<T> node, StringBuilder stringBuilder) { // recursive method
        if (node != null) { // if node is not null
            allElementsToStringBuilder(node.left, stringBuilder); // go to left
            stringBuilder.append(node.data).append(" "); // add node to string
            allElementsToStringBuilder(node.right, stringBuilder); // go to right
        }
        return stringBuilder; // return string
    }
}
