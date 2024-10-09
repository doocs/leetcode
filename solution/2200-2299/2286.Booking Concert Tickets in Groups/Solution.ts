class Node {
    l: number;
    r: number;
    mx: number;
    s: number;

    constructor() {
        this.l = 0;
        this.r = 0;
        this.mx = 0;
        this.s = 0;
    }
}

class SegmentTree {
    private tr: Node[];
    private m: number;

    constructor(n: number, m: number) {
        this.m = m;
        this.tr = Array.from({ length: n << 2 }, () => new Node());
        this.build(1, 1, n);
    }

    private build(u: number, l: number, r: number): void {
        this.tr[u].l = l;
        this.tr[u].r = r;
        if (l === r) {
            this.tr[u].s = this.m;
            this.tr[u].mx = this.m;
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
        this.pushup(u);
    }

    public modify(u: number, x: number, v: number): void {
        if (this.tr[u].l === x && this.tr[u].r === x) {
            this.tr[u].s = v;
            this.tr[u].mx = v;
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

    public querySum(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].s;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let v = 0;
        if (l <= mid) {
            v += this.querySum(u << 1, l, r);
        }
        if (r > mid) {
            v += this.querySum((u << 1) | 1, l, r);
        }
        return v;
    }

    public queryIdx(u: number, l: number, r: number, k: number): number {
        if (this.tr[u].mx < k) {
            return 0;
        }
        if (this.tr[u].l === this.tr[u].r) {
            return this.tr[u].l;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (this.tr[u << 1].mx >= k) {
            return this.queryIdx(u << 1, l, r, k);
        }
        if (r > mid) {
            return this.queryIdx((u << 1) | 1, l, r, k);
        }
        return 0;
    }

    private pushup(u: number): void {
        this.tr[u].s = this.tr[u << 1].s + this.tr[(u << 1) | 1].s;
        this.tr[u].mx = Math.max(this.tr[u << 1].mx, this.tr[(u << 1) | 1].mx);
    }
}

class BookMyShow {
    private n: number;
    private m: number;
    private tree: SegmentTree;

    constructor(n: number, m: number) {
        this.n = n;
        this.m = m;
        this.tree = new SegmentTree(n, m);
    }

    public gather(k: number, maxRow: number): number[] {
        ++maxRow;
        const i = this.tree.queryIdx(1, 1, maxRow, k);
        if (i === 0) {
            return [];
        }
        const s = this.tree.querySum(1, i, i);
        this.tree.modify(1, i, s - k);
        return [i - 1, this.m - s];
    }

    public scatter(k: number, maxRow: number): boolean {
        ++maxRow;
        if (this.tree.querySum(1, 1, maxRow) < k) {
            return false;
        }
        let i = this.tree.queryIdx(1, 1, maxRow, 1);
        for (let j = i; j <= this.n; ++j) {
            const s = this.tree.querySum(1, j, j);
            if (s >= k) {
                this.tree.modify(1, j, s - k);
                return true;
            }
            k -= s;
            this.tree.modify(1, j, 0);
        }
        return true;
    }
}
