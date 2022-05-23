class MyQueue {
    private inStack: number[];
    private outStack: number[];

    constructor() {
        this.inStack = [];
        this.outStack = [];
    }

    push(x: number): void {
        this.inStack.push(x);
    }

    pop(): number {
        if (this.outStack.length === 0) {
            this.inToOut();
        }
        return this.outStack.pop() ?? -1;
    }

    peek(): number {
        if (this.outStack.length === 0) {
            this.inToOut();
        }
        return this.outStack[this.outStack.length - 1] ?? -1;
    }

    empty(): boolean {
        return this.inStack.length === 0 && this.outStack.length === 0;
    }

    inToOut() {
        while (this.inStack.length !== 0) {
            this.outStack.push(this.inStack.pop());
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * var obj = new MyQueue()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.empty()
 */
