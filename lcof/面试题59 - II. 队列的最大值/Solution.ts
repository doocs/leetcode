class MaxQueue {
    private queue: number[];
    private deque: number[];

    constructor() {
        this.queue = [];
        this.deque = [];
    }

    max_value(): number {
        return this.deque[0] ?? -1;
    }

    push_back(value: number): void {
        this.queue.push(value);
        while (
            this.deque.length !== 0 &&
            this.deque[this.deque.length - 1] < value
        ) {
            this.deque.pop();
        }
        this.deque.push(value);
    }

    pop_front(): number {
        const res = this.queue.shift();
        if (res === this.deque[0]) {
            this.deque.shift();
        }
        return res ?? -1;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * var obj = new MaxQueue()
 * var param_1 = obj.max_value()
 * obj.push_back(value)
 * var param_3 = obj.pop_front()
 */
