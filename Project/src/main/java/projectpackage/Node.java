package projectpackage;

public class Node<T extends Comparable<T>> {

    T data;
    Node<T> left;
    Node<T> right;

    public Node() {
    }

    public Node(T data) {
        this.data = data;
    }

}
