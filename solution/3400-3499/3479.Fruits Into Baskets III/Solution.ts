class SegmentTree {
    nums: number[];
    tr: number[];

    constructor(nums: number[]) {
        this.nums = nums;
        const n = nums.length;
        this.tr = Array(n * 4).fill(0);
        this.build(1, 1, n);
    }

    build(u: number, l: number, r: number): void {
        if (l === r) {
            this.tr[u] = this.nums[l - 1];
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u * 2, l, mid);
        this.build(u * 2 + 1, mid + 1, r);
        this.pushup(u);
    }

    modify(u: number, l: number, r: number, i: number, v: number): void {
        if (l === r) {
            this.tr[u] = v;
            return;
        }
        const mid = (l + r) >> 1;
        if (i <= mid) {
            this.modify(u * 2, l, mid, i, v);
        } else {
            this.modify(u * 2 + 1, mid + 1, r, i, v);
        }
        this.pushup(u);
    }

    query(u: number, l: number, r: number, v: number): number {
        if (this.tr[u] < v) {
            return -1;
        }
        if (l === r) {
            return l;
        }
        const mid = (l + r) >> 1;
        if (this.tr[u * 2] >= v) {
            return this.query(u * 2, l, mid, v);
        }
        return this.query(u * 2 + 1, mid + 1, r, v);
    }

    pushup(u: number): void {
        this.tr[u] = Math.max(this.tr[u * 2], this.tr[u * 2 + 1]);
    }
}

function numOfUnplacedFruits(fruits: number[], baskets: number[]): number {
    const tree = new SegmentTree(baskets);
    const n = baskets.length;
    let ans = 0;
    for (const x of fruits) {
        const i = tree.query(1, 1, n, x);
        if (i < 0) {
            ans++;
        } else {
            tree.modify(1, 1, n, i, 0);
        }
    }
    return ans;
}
