class Node {
    l: number = 0;
    r: number = 0;
    v: number = 0;
}

class SegmentTree {
    private tr: Node[];

    constructor(n: number) {
        this.tr = Array(4 * n);
        for (let i = 0; i < 4 * n; i++) {
            this.tr[i] = new Node();
        }
        this.build(1, 1, n);
    }

    private build(u: number, l: number, r: number): void {
        this.tr[u].l = l;
        this.tr[u].r = r;
        if (l === r) return;
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
    }

    modify(u: number, x: number, v: number): void {
        if (this.tr[u].l === x && this.tr[u].r === x) {
            this.tr[u].v += v;
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

    private pushup(u: number): void {
        this.tr[u].v = this.tr[u << 1].v + this.tr[(u << 1) | 1].v;
    }

    query(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].v;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let res = 0;
        if (l <= mid) {
            res += this.query(u << 1, l, r);
        }
        if (r > mid) {
            res += this.query((u << 1) | 1, l, r);
        }
        return res;
    }
}

function goodTriplets(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const pos = new Map<number, number>();
    nums2.forEach((v, i) => pos.set(v, i + 1));

    const tree = new SegmentTree(n);
    let ans = 0;

    for (const num of nums1) {
        const p = pos.get(num)!;
        const left = tree.query(1, 1, p);
        const total = tree.query(1, 1, n);
        const right = n - p - (total - left);
        ans += left * right;
        tree.modify(1, p, 1);
    }

    return ans;
}
