class MyLinkedList {
    e: Array<number>;
    ne: Array<number>;
    idx: number;
    head: number;
    cnt: number;

    constructor() {
        this.e = new Array(1010).fill(0);
        this.ne = new Array(1010).fill(0);
        this.head = -1;
        this.idx = 0;
        this.cnt = 0;
    }

    get(index: number): number {
        if (index < 0 || index >= this.cnt) {
            return -1;
        }
        let i = this.head;
        while (index--) {
            i = this.ne[i];
        }
        return this.e[i];
    }

    addAtHead(val: number): void {
        this.e[this.idx] = val;
        this.ne[this.idx] = this.head;
        this.head = this.idx++;
        this.cnt++;
    }

    addAtTail(val: number): void {
        this.addAtIndex(this.cnt, val);
    }

    addAtIndex(index: number, val: number): void {
        if (index > this.cnt) {
            return;
        }
        if (index <= 0) {
            this.addAtHead(val);
            return;
        }
        let i = this.head;
        while (--index) {
            i = this.ne[i];
        }
        this.e[this.idx] = val;
        this.ne[this.idx] = this.ne[i];
        this.ne[i] = this.idx++;
        this.cnt++;
    }

    deleteAtIndex(index: number): void {
        if (index < 0 || index >= this.cnt) {
            return;
        }
        this.cnt--;
        if (index == 0) {
            this.head = this.ne[this.head];
            return;
        }
        let i = this.head;
        while (--index) {
            i = this.ne[i];
        }
        this.ne[i] = this.ne[this.ne[i]];
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = new MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(val)
 * obj.addAtTail(val)
 * obj.addAtIndex(index,val)
 * obj.deleteAtIndex(index)
 */
