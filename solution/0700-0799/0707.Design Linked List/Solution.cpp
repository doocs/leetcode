class MyLinkedList {
public:
    int e[1000];
    int ne[1000];
    int head = -1;
    int idx = 0;
    int size = 0;

    MyLinkedList() {
    }

    int get(int index) {
        if (index < 0 || index >= size) return -1;
        int i = head;
        for (; index > 0; i = ne[i], index--)
            ;
        return e[i];
    }

    void addAtHead(int val) {
        e[idx] = val;
        ne[idx] = head;
        head = idx++;
        size++;
    }

    void addAtTail(int val) {
        addAtIndex(size, val);
    }

    void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        int i = head;
        for (; index > 1; i = ne[i], index--)
            ;
        e[idx] = val;
        ne[idx] = ne[i];
        ne[i] = idx++;
        size++;
    }

    void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        size--;
        if (index == 0) {
            head = ne[head];
            return;
        }
        int i = head;
        for (; index > 1; i = ne[i], index--)
            ;
        ne[i] = ne[ne[i]];
    }
};

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList* obj = new MyLinkedList();
 * int param_1 = obj->get(index);
 * obj->addAtHead(val);
 * obj->addAtTail(val);
 * obj->addAtIndex(index,val);
 * obj->deleteAtIndex(index);
 */