class CustomStack {
    private stk: number[];
    private add: number[];
    private i: number;

    constructor(maxSize: number) {
        this.stk = Array(maxSize).fill(0);
        this.add = Array(maxSize).fill(0);
        this.i = 0;
    }

    push(x: number): void {
        if (this.i < this.stk.length) {
            this.stk[this.i++] = x;
        }
    }

    pop(): number {
        if (this.i <= 0) {
            return -1;
        }
        const ans = this.stk[--this.i] + this.add[this.i];
        if (this.i > 0) {
            this.add[this.i - 1] += this.add[this.i];
        }
        this.add[this.i] = 0;
        return ans;
    }

    increment(k: number, val: number): void {
        if (this.i > 0) {
            this.add[Math.min(this.i, k) - 1] += val;
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
