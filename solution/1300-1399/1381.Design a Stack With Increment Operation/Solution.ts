class CustomStack {
    maxSize: number;
    size: number;
    stack: Array<number>;
    constructor(maxSize: number) {
        this.maxSize = maxSize;
        this.size = 0;
        this.stack = [];
    }

    push(x: number): void {
        if (this.size >= this.maxSize) return;
        this.size++;
        this.stack.unshift(x);
    }

    pop(): number {
        if (!this.size) return -1;
        this.size--;
        return this.stack.shift();
    }

    increment(k: number, val: number): void {
        for (let i = Math.max(this.size - k, 0); i < this.size; i++) {
            this.stack[i] = this.stack[i] + val;
        }
    }
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * var obj = new CustomStack(maxSize)
 * obj.push(x)
 * var param_2 = obj.pop()
 * obj.increment(k,val)
 */
