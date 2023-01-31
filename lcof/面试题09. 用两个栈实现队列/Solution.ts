class CQueue {
    private stk1: number[];
    private stk2: number[];

    constructor() {
        this.stk1 = [];
        this.stk2 = [];
    }

    appendTail(value: number): void {
        this.stk1.push(value);
    }

    deleteHead(): number {
        if (this.stk2.length == 0) {
            while (this.stk1.length) {
                this.stk2.push(this.stk1.pop());
            }
        }
        return this.stk2.length == 0 ? -1 : this.stk2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
