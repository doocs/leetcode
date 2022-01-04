class CQueue {
    private stack1: number[];
    private stack2: number[];
    constructor() {
        this.stack1 = [];
        this.stack2 = [];
    }

    appendTail(value: number): void {
        this.stack1.push(value);
    }

    move(): void {
        while (this.stack1.length != 0) {
            this.stack2.push(this.stack1.pop());
        }
    }

    deleteHead(): number {
        if (this.stack2.length == 0) {
            this.move();
        }
        return this.stack2.length == 0 ? -1 : this.stack2.pop();
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * var obj = new CQueue()
 * obj.appendTail(value)
 * var param_2 = obj.deleteHead()
 */
