function gcd(a: number, b: number): number {
    while (b !== 0) {
        [a, b] = [b, a % b];
    }
    return a;
}

class SegNode {
    l: number;
    r: number;
    g: number;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.g = 0;
    }
}

class SegmentTree {
    tr: SegNode[];

    constructor(n: number) {
        this.tr = Array(n << 2);
        this.build(1, 1, n);
    }

    build(u: number, l: number, r: number): void {
        this.tr[u] = new SegNode(l, r);
        if (l === r) {
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
    }

    pushup(u: number): void {
        this.tr[u].g = gcd(this.tr[u << 1].g, this.tr[(u << 1) | 1].g);
    }

    modify(u: number, x: number, v: number): void {
        if (this.tr[u].l === this.tr[u].r) {
            this.tr[u].g = v;
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

    query(u: number, l: number, r: number): number {
        if (l > r) {
            return 0;
        }
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].g;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (r <= mid) {
            return this.query(u << 1, l, r);
        }
        if (l > mid) {
            return this.query((u << 1) | 1, l, r);
        }
        return gcd(this.query(u << 1, l, mid), this.query((u << 1) | 1, mid + 1, r));
    }
}

function countGoodSubseq(nums: number[], p: number, queries: number[][]): number {
    const n = nums.length;
    const tree = new SegmentTree(n);
    let cnt = 0;
    for (let i = 0; i < n; ++i) {
        if (nums[i] % p === 0) {
            tree.modify(1, i + 1, nums[i]);
            ++cnt;
        }
    }

    let ans = 0;
    for (const [idx, val] of queries) {
        if (nums[idx] % p === 0) {
            tree.modify(1, idx + 1, 0);
            --cnt;
        }
        if (val % p === 0) {
            tree.modify(1, idx + 1, val);
            ++cnt;
        }
        nums[idx] = val;

        if (tree.tr[1].g !== p) {
            continue;
        }
        if (cnt < n || n > 6) {
            ++ans;
            continue;
        }
        for (let i = 1; i <= n; ++i) {
            const leftG = tree.query(1, 1, i - 1);
            const rightG = tree.query(1, i + 1, n);
            if (gcd(leftG, rightG) === p) {
                ++ans;
                break;
            }
        }
    }
    return ans;
}
