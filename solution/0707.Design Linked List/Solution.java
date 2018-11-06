class MyLinkedList {
    
    private class Node {
        int val;
        Node next;
        Node(int val) {
            this.val = val;
        }
    }
    
    private Node dummy;
    
    private int size;
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        dummy = new Node(-1);
        size = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node cur = dummy;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        return cur.next.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.next = dummy.next;
        dummy.next = node;
        ++size;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node cur = dummy;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node node = new Node(val);
        cur.next = node;
        ++size;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) {
            return;
        }
        Node cur = dummy;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;                                                               
        }
        Node node = new Node(val);
        node.next = cur.next;
        cur.next = node;
        ++size;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        Node cur = dummy;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        --size;
        
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */