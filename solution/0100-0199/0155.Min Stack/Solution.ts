class MinStack {
    private stack: number[];
    private min: number[];

    constructor() {
        this.stack = [];
        this.min = [];
    }

    push(val: number): void {
        this.stack.push(val);
        if (val <= (this.min[this.min.length - 1] ?? Infinity)) {
            this.min.push(val);
        }
    }

    pop(): void {
        if (this.stack.pop() === this.min[this.min.length - 1]) {
            this.min.pop();
        }
    }

    top(): number {
        return this.stack[this.stack.length - 1];
    }

    getMin(): number {
        return this.min[this.min.length - 1];
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * var obj = new MinStack()
 * obj.push(val)
 * obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.getMin()
 */
