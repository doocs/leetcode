class SortedStack {
    stack: number[];
    constructor() {
        this.stack = [];
    }

    push(val: number): void {
        let t = [];
        while (!this.isEmpty() && this.peek() < val) {
            t.push(this.stack.pop());
        }
        this.stack.push(val);
        while (t.length > 0) {
            this.stack.push(t.pop());
        }
    }

    pop(): void {
        this.stack.pop();
    }

    peek(): number {
        return this.isEmpty() ? -1 : this.stack[this.stack.length - 1];
    }

    isEmpty(): boolean {
        return this.stack.length == 0;
    }
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * var obj = new SortedStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.peek()
 * var param_4 = obj.isEmpty()
 */
