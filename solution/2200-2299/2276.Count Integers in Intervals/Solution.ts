class CountIntervals {
    left: null | CountIntervals;
    right: null | CountIntervals;
    start: number;
    end: number;
    sum: number;
    constructor(start: number = 0, end: number = 10 ** 9) {
        this.left = null;
        this.right = null;
        this.start = start;
        this.end = end;
        this.sum = 0;
    }

    add(left: number, right: number): void {
        if (this.sum == this.end - this.start + 1) return;
        if (left <= this.start && right >= this.end) {
            this.sum = this.end - this.start + 1;
            return;
        }
        let mid = (this.start + this.end) >> 1;
        if (!this.left) this.left = new CountIntervals(this.start, mid);
        if (!this.right) this.right = new CountIntervals(mid + 1, this.end);
        if (left <= mid) this.left.add(left, right);
        if (right > mid) this.right.add(left, right);
        this.sum = this.left.sum + this.right.sum;
    }

    count(): number {
        return this.sum;
    }
}

/**
 * Your CountIntervals object will be instantiated and called as such:
 * var obj = new CountIntervals()
 * obj.add(left,right)
 * var param_2 = obj.count()
 */
