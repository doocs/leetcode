class MovingAverage {
    private s: number = 0;
    private cnt: number = 0;
    private data: number[];

    constructor(size: number) {
        this.data = Array(size).fill(0);
    }

    next(val: number): number {
        const i = this.cnt % this.data.length;
        this.s += val - this.data[i];
        this.data[i] = val;
        this.cnt++;
        return this.s / Math.min(this.cnt, this.data.length);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * var obj = new MovingAverage(size)
 * var param_1 = obj.next(val)
 */
