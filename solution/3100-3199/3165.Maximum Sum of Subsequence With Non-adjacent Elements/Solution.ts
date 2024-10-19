class Node {
    s00 = 0;
    s01 = 0;
    s10 = 0;
    s11 = 0;

    constructor(
        public l: number,
        public r: number,
    ) {}
}

class SegmentTree {
    tr: Node[];

    constructor(n: number) {
        this.tr = Array(n * 4);
        this.build(1, 1, n);
    }

    build(u: number, l: number, r: number) {
        this.tr[u] = new Node(l, r);
        if (l === r) {
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
    }

    query(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].s11;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let ans = 0;
        if (r <= mid) {
            ans = this.query(u << 1, l, r);
        }
        if (l > mid) {
            ans = Math.max(ans, this.query((u << 1) | 1, l, r));
        }
        return ans;
    }

    pushup(u: number) {
        const left = this.tr[u << 1];
        const right = this.tr[(u << 1) | 1];
        this.tr[u].s00 = Math.max(left.s00 + right.s10, left.s01 + right.s00);
        this.tr[u].s01 = Math.max(left.s00 + right.s11, left.s01 + right.s01);
        this.tr[u].s10 = Math.max(left.s10 + right.s10, left.s11 + right.s00);
        this.tr[u].s11 = Math.max(left.s10 + right.s11, left.s11 + right.s01);
    }

    modify(u: number, x: number, v: number) {
        if (this.tr[u].l === this.tr[u].r) {
            this.tr[u].s11 = Math.max(0, v);
            return;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (x <= mid) {
            this.modify(u << 1, x, v);
        } else {
            this.modify((u << 1) | 1, x, v);
        }
        this.pushup(u);
    }
}

function maximumSumSubsequence(nums: number[], queries: number[][]): number {
    const n = nums.length;
    const tree = new SegmentTree(n);
    for (let i = 0; i < n; i++) {
        tree.modify(1, i + 1, nums[i]);
    }
    let ans = 0;
    const mod = 1e9 + 7;
    for (const [i, x] of queries) {
        tree.modify(1, i + 1, x);
        ans = (ans + tree.query(1, 1, n)) % mod;
    }
    return ans;
}
