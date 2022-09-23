class MyLinkedList {
private:
    ListNode* dummy = new ListNode();
    int cnt = 0;

public:
    MyLinkedList() {
    }

    int get(int index) {
        if (index < 0 || index >= cnt) {
            return -1;
        }
        auto cur = dummy->next;
        while (index--) {
            cur = cur->next;
        }
        return cur->val;
    }

    void addAtHead(int val) {
        addAtIndex(0, val);
    }

    void addAtTail(int val) {
        addAtIndex(cnt, val);
    }

    void addAtIndex(int index, int val) {
        if (index > cnt) {
            return;
        }
        auto pre = dummy;
        while (index-- > 0) {
            pre = pre->next;
        }
        pre->next = new ListNode(val, pre->next);
        ++cnt;
    }

    void deleteAtIndex(int index) {
        if (index >= cnt) {
            return;
        }
        auto pre = dummy;
        while (index-- > 0) {
            pre = pre->next;
        }
        auto t = pre->next;
        pre->next = t->next;
        t->next = nullptr;
        --cnt;
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