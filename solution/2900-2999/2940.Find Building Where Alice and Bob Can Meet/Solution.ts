class BinaryIndexedTree {
    private n: number;
    private c: number[];
    private inf: number = 1 << 30;

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(this.inf);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.min(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mi = this.inf;
        while (x > 0) {
            mi = Math.min(mi, this.c[x]);
            x -= x & -x;
        }
        return mi === this.inf ? -1 : mi;
    }
}

function leftmostBuildingQueries(heights: number[], queries: number[][]): number[] {
    const n = heights.length;
    const m = queries.length;
    for (const q of queries) {
        if (q[0] > q[1]) {
            [q[0], q[1]] = [q[1], q[0]];
        }
    }
    const idx: number[] = Array(m)
        .fill(0)
        .map((_, i) => i);
    idx.sort((i, j) => queries[j][1] - queries[i][1]);
    const tree = new BinaryIndexedTree(n);
    const ans: number[] = Array(m).fill(-1);
    const s = [...heights];
    s.sort((a, b) => a - b);
    const search = (x: number) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (s[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    let j = n - 1;
    for (const i of idx) {
        const [l, r] = queries[i];
        while (j > r) {
            const k = n - search(heights[j]) + 1;
            tree.update(k, j);
            --j;
        }
        if (l === r || heights[l] < heights[r]) {
            ans[i] = r;
        } else {
            const k = n - search(heights[l]);
            ans[i] = tree.query(k);
        }
    }
    return ans;
}
