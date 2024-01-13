class SortedStack {
    private stack: number[];

    constructor() {
        this.stack = [];
    }

    push(val: number): void {
        if (this.isEmpty() || this.peek() > val) {
            this.stack.push(val);
            return;
        }

        const tmp = this.stack.pop();
        this.push(val);
        this.stack.push(tmp);
    }

    pop(): void {
        this.stack.pop();
    }

    peek(): number {
        return this.stack[this.stack.length - 1] ?? -1;
    }

    isEmpty(): boolean {
        return this.stack.length === 0;
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
