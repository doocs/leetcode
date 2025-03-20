class MovingAverage {
    private q: number[] = [];
    private s: number = 0;
    private n: number;

    constructor(size: number) {
        this.n = size;
    }

    next(val: number): number {
        if (this.q.length === this.n) {
            this.s -= this.q.shift()!;
        }
        this.q.push(val);
        this.s += val;
        return this.s / this.q.length;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = new MovingAverage(size)
 * var param_1 = obj.next(val)
 */
