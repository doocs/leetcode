class MyStack {
    q1: number[] = [];
    q2: number[] = [];

    constructor() {}

    push(x: number): void {
        this.q2.push(x);
        while (this.q1.length) {
            this.q2.push(this.q1.shift()!);
        }
        [this.q1, this.q2] = [this.q2, this.q1];
    }

    pop(): number {
        return this.q1.shift()!;
    }

    top(): number {
        return this.q1[0];
    }

    empty(): boolean {
        return this.q1.length === 0;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * var obj = new MyStack()
 * obj.push(x)
 * var param_2 = obj.pop()
 * var param_3 = obj.top()
 * var param_4 = obj.empty()
 */
