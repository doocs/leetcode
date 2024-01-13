class MyLinkedList {
private:
    int e[1010], ne[1010];
    int head = -1, idx = 0, cnt = 0;

public:
    MyLinkedList() {
    }

    int get(int index) {
        if (index < 0 || index >= cnt) {
            return -1;
        }
        int i = head;
        while (index--) {
            i = ne[i];
        }
        return e[i];
    }

    void addAtHead(int val) {
        e[idx] = val;
        ne[idx] = head;
        head = idx++;
        ++cnt;
    }

    void addAtTail(int val) {
        addAtIndex(cnt, val);
    }

    void addAtIndex(int index, int val) {
        if (index > cnt) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        int i = head;
        while (--index) {
            i = ne[i];
        }
        e[idx] = val;
        ne[idx] = ne[i];
        ne[i] = idx++;
        ++cnt;
    }

    void deleteAtIndex(int index) {
        if (index < 0 || index >= cnt) {
            return;
        }
        --cnt;
        if (index == 0) {
            head = ne[head];
            return;
        }
        int i = head;
        while (--index) {
            i = ne[i];
        }
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