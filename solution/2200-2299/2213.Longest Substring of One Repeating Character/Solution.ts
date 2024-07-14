class Node {
    l: number;
    r: number;
    lmx: number;
    rmx: number;
    mx: number;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.lmx = 1;
        this.rmx = 1;
        this.mx = 1;
    }
}

class SegmentTree {
    private s: string[];
    private tr: Node[];

    constructor(s: string) {
        this.s = s.split('');
        this.tr = Array(s.length * 4)
            .fill(null)
            .map(() => new Node(0, 0));
        this.build(1, 1, s.length);
    }

    private build(u: number, l: number, r: number): void {
        this.tr[u] = new Node(l, r);
        if (l === r) {
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
        this.pushup(u);
    }

    public modify(u: number, x: number, v: string): void {
        if (this.tr[u].l === x && this.tr[u].r === x) {
            this.s[x - 1] = v;
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

    public query(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].mx;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let ans = 0;
        if (r <= mid) {
            ans = this.query(u << 1, l, r);
        } else if (l > mid) {
            ans = Math.max(ans, this.query((u << 1) | 1, l, r));
        } else {
            ans = Math.max(this.query(u << 1, l, r), this.query((u << 1) | 1, l, r));
        }
        return ans;
    }

    private pushup(u: number): void {
        const root = this.tr[u];
        const left = this.tr[u << 1];
        const right = this.tr[(u << 1) | 1];
        root.mx = Math.max(left.mx, right.mx);
        root.lmx = left.lmx;
        root.rmx = right.rmx;
        const a = left.r - left.l + 1;
        const b = right.r - right.l + 1;
        if (this.s[left.r - 1] === this.s[right.l - 1]) {
            if (left.lmx === a) {
                root.lmx += right.lmx;
            }
            if (right.rmx === b) {
                root.rmx += left.rmx;
            }
            root.mx = Math.max(root.mx, left.rmx + right.lmx);
        }
    }
}

function longestRepeating(s: string, queryCharacters: string, queryIndices: number[]): number[] {
    const tree = new SegmentTree(s);
    const k = queryIndices.length;
    const ans: number[] = new Array(k);
    const n = s.length;
    for (let i = 0; i < k; ++i) {
        const x = queryIndices[i] + 1;
        const v = queryCharacters[i];
        tree.modify(1, x, v);
        ans[i] = tree.query(1, 1, n);
    }
    return ans;
}
