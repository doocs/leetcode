class MyLinkedList {
    private int[] e = new int[1010];
    private int[] ne = new int[1010];
    private int head = -1;
    private int idx;
    private int cnt;

    public MyLinkedList() {
    }

    public int get(int index) {
        if (index < 0 || index >= cnt) {
            return -1;
        }
        int i = head;
        while (index-- > 0) {
            i = ne[i];
        }
        return e[i];
    }

    public void addAtHead(int val) {
        e[idx] = val;
        ne[idx] = head;
        head = idx++;
        ++cnt;
    }

    public void addAtTail(int val) {
        addAtIndex(cnt, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > cnt) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        int i = head;
        while (--index > 0) {
            i = ne[i];
        }
        e[idx] = val;
        ne[idx] = ne[i];
        ne[i] = idx++;
        ++cnt;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= cnt) {
            return;
        }
        --cnt;
        if (index == 0) {
            head = ne[head];
            return;
        }
        int i = head;
        while (--index > 0) {
            i = ne[i];
        }
        ne[i] = ne[ne[i]];
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