public class CustomLinkedList {
    Node head;

    public void insert(int data) {
        Node node = new Node(data);
        node.data = data;

        if (head == null) {
            head = node;
        } else {
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }

    public void show() {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    public boolean contains(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void delete(int value){
        Node current = head;
        Node previous = null;

        if (head == null) {
            System.out.println("Queue is empty");
            return;
        }

        // Check if head needs to be removed
        if (head.data == value) {
            head = head.next;
            return;
        }

        // Find the node to delete
        while (current != null && current.data != value) {
            previous = current;
            current = current.next;
        }

        // If the value was not found
        if (current == null) {
            System.out.println("Value not found");
            return;
        }

        // Remove the node
        previous.next = current.next;
    }

    public int size() {
        int size = 0;
        Node n = head;
        while (n != null) {
            size++;
            n = n.next;
        }
        return size;
    }


    public int get(int i) {
        int j = 0;
        Node n = head;
        while (n != null && j < i) {
            n = n.next;
            j++;
        }
        if (n == null) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return n.data;
    }


}
