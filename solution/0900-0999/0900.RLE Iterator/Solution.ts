class RLEIterator {
    private encoding: number[];
    private i: number;
    private j: number;

    constructor(encoding: number[]) {
        this.encoding = encoding;
        this.i = 0;
        this.j = 0;
    }

    next(n: number): number {
        while (this.i < this.encoding.length) {
            if (this.encoding[this.i] - this.j < n) {
                n -= this.encoding[this.i] - this.j;
                this.i += 2;
                this.j = 0;
            } else {
                this.j += n;
                return this.encoding[this.i + 1];
            }
        }
        return -1;
    }
}

/**
 * Your RLEIterator object will be instantiated and called as such:
 * var obj = new RLEIterator(encoding)
 * var param_1 = obj.next(n)
 */
