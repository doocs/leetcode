class SortedStack {
    private stk: number[] = [];
    constructor() {}

    push(val: number): void {
        const t: number[] = [];
        while (this.stk.length > 0 && this.stk.at(-1)! < val) {
            t.push(this.stk.pop()!);
        }
        this.stk.push(val);
        while (t.length > 0) {
            this.stk.push(t.pop()!);
        }
    }

    pop(): void {
        if (!this.isEmpty()) {
            this.stk.pop();
        }
    }

    peek(): number {
        return this.isEmpty() ? -1 : this.stk.at(-1)!;
    }

    isEmpty(): boolean {
        return this.stk.length === 0;
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
