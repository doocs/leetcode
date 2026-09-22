class Node {
    l: number;
    r: number;
    prod: number;
    cnt: number[];

    constructor(l: number, r: number, k: number) {
        this.l = l;
        this.r = r;
        this.prod = 1;
        this.cnt = Array(k).fill(0);
    }
}

class SegmentTree {
    private k: number;
    private tr: Node[];

    constructor(nums: number[], k: number) {
        this.k = k;
        this.tr = Array(nums.length << 2);
        this.build(1, 1, nums.length, nums);
    }

    private merge(a: Node, b: Node): Node {
        const c = new Node(0, 0, this.k);
        c.prod = (a.prod * b.prod) % this.k;
        c.cnt = a.cnt.slice();
        for (let r = 0; r < this.k; ++r) {
            c.cnt[(a.prod * r) % this.k] += b.cnt[r];
        }
        return c;
    }

    private pushup(u: number): void {
        const p = this.merge(this.tr[u << 1], this.tr[(u << 1) | 1]);
        this.tr[u].prod = p.prod;
        this.tr[u].cnt = p.cnt;
    }

    private build(u: number, l: number, r: number, nums: number[]): void {
        this.tr[u] = new Node(l, r, this.k);
        if (l === r) {
            const v = nums[l - 1] % this.k;
            this.tr[u].prod = v;
            this.tr[u].cnt[v] = 1;
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid, nums);
        this.build((u << 1) | 1, mid + 1, r, nums);
        this.pushup(u);
    }

    modify(u: number, x: number, v: number): void {
        if (this.tr[u].l === this.tr[u].r) {
            v %= this.k;
            this.tr[u].prod = v;
            this.tr[u].cnt.fill(0);
            this.tr[u].cnt[v] = 1;
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

    query(u: number, l: number, r: number): Node {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u];
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (r <= mid) {
            return this.query(u << 1, l, r);
        }
        if (l > mid) {
            return this.query((u << 1) | 1, l, r);
        }
        return this.merge(this.query(u << 1, l, r), this.query((u << 1) | 1, l, r));
    }
}

function resultArray(nums: number[], k: number, queries: number[][]): number[] {
    const n = nums.length;
    const tree = new SegmentTree(nums, k);
    const ans: number[] = [];
    for (const [idx, val, start, x] of queries) {
        tree.modify(1, idx + 1, val);
        ans.push(tree.query(1, start + 1, n).cnt[x]);
    }
    return ans;
}
