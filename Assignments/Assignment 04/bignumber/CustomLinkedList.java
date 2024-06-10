package bignumber;

/**
 * A custom implementation of a singly linked list
 * built without using the built-in linear data structures in Java
 */
public class CustomLinkedList {
    /**
     * A singly linked list node implementation with integer data
     */
    public static class Node {
        private int data;
        private Node next;

        /**
         * Create a new node with the given data
         * @param data the data to be stored in the node
         */
        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        /**
         * Create a new node with the given data and the next node
         * @param data the data to be stored in the node
         */
        public void setData(int data) {
            this.data = data;
        }

        /**
         * Get the data stored in the node
         * @return the data stored in the node
         */
        public int getData() {
            return data;
        }

        /**
         * Set the next node for a node
         * @param next the next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Get the next node
         * @return the next node
         */
        public Node getNext() {
            return next;
        }

        @Override
        public String toString() {
            return String.valueOf(data);
        }
    }

    private Node head;

    // values to avoid iterating through the list
    private Node tail;
    private int length = 0;

    public CustomLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    /**
     * Get the length of the list
     * @return the length of the list
     */
    public int length() {
        return this.length;
    }

    /**
     * Get the head of the list
     * @return the head of the list
     */
    public CustomLinkedList getCopy() {
        CustomLinkedList copy = new CustomLinkedList();
        for(int i = 0; i < this.length; i++) {
            copy.append(this.get(i).getData());
        }

        return copy;
    }

    /**
     * Append a node to the end of the list
     * @param node the node to be appended
     */
    public void append(Node node) {
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = node;
        }
        this.length++;
    }

    /**
     * Append a node with the given data to the end of the list
     * @param data the data to be appended
     */
    public void append(int data) {
        Node node = new Node(data);
        append(node);
    }

    /**
     * insert a node at the given index
     * @param node the node to be inserted
     * @param index the index to insert the node
     */
    public void insert(Node node, int index) {
        if (index == 0) {
            node.setNext(this.head);
            this.head = node;
        }
        else if (index == -1) {
            append(node);
        }
        else {
            Node previous = this.get(index - 1);
            node.setNext(previous.getNext());
            previous.setNext(node);
        }
        this.length++;
    }

    /**
     * insert a node with the given data at the given index
     * @param data the data to be inserted
     * @param index the index to insert the node
     */
    public void insert(int data, int index) {
        Node node = new Node(data);
        insert(node, index);
    }


    /**
     * get the node at the given index
     * negative index is allowed, meaning the index is counted from the end of the list
     * @param index the index of the node to get
     * @return the node at the given index
     */
    public Node get(int index) {
        int targetIndex = -1;

        if (index == 0) {
            return this.head;
        }
        else if (index == -1) {
            return this.tail;
        }
        else if (index > 0) {
            if (index >= this.length) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }

            targetIndex = index;
        }
        else {
            if (-(index) > this.length) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }

            targetIndex = this.length + index;
        }

        Node current = this.head;
        for (int i = 0; i < targetIndex; i++) {
            if (current.getNext() == null) {
                throw new IllegalStateException("Unexpected null pointer in list");
            }
            current = current.getNext();
        }
        return current;
    }

    /**
     * get the data at the given index
     * negative index is allowed, meaning the index is counted from the end of the list
     * @param index the index of the node to get
     * @return the data at the given index
     */
    public int getValueAt(int index) {
        return get(index).getData();
    }

    /**
     * pop the last element from the list
     * @return the data of the popped node
     */
    public int pop() {
        if (this.length == 0) {
            throw new IllegalStateException("Cannot pop from an empty list");
        }
        else if (this.length == 1) {
            int data = this.head.getData();
            this.head = null;
            this.tail = null;
            this.length--;
            return data;
        }
        else {
            Node newTail = this.get(-2);
            int data = this.tail.getData();
            this.tail = newTail;
            this.tail.setNext(null);
            this.length--;
            return data;
        }
    }

    /**
     * pop the node at the given index
     * negative index is allowed, meaning the index is counted from the end of the list
     * @param index the index of the node to pop
     * @return the data of the popped node
     */
    public int pop(int index) {
        if (index == 0) {
            int data = this.head.getData();
            this.head = this.head.getNext();
            this.length--;
        }
        else if (index == -1) {
            return pop();
        }

        Node previous = this.get(index - 1);
        Node current = previous.getNext();
        int data = current.getData();
        previous.setNext(current.getNext());
        this.length--;
        return data;
    }


    /**
     * reverse the list
     * @return this reversed
     */
    public CustomLinkedList reverse() {
        this.tail = this.head;

        Node last = null;
        Node curr = this.head;
        Node next = null;

        if (curr == null) {
            return this;
        }

        while (curr != null) {
            next = curr.getNext();
            curr.setNext(last);
            last = curr;
            curr = next;
        }

        this.head = last;
        return this;
    }

    /**
     * get a reverse copy of the list
     * @return the reverse of the list
     */
    public CustomLinkedList getReverseCopy() {
        return this.getCopy().reverse();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = this.head;
        while (current != null) {
            sb.append(current.getData());
            current = current.getNext();
        }
        return sb.toString();
    }

    /**
     * get a string representation of this.number, useful for debugging
     * @return the list representation of the list
     */
    public String toListRepr() {
        StringBuilder sb = new StringBuilder();
        Node current = this.head;
        while (current != null) {
            sb.append("[")
              .append(current.getData())
              .append("]");

            if (current.getNext() != null) {
                sb.append(" -> ");
            }

            current = current.getNext();
        }
        return sb.toString();
    }
}
