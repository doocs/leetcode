class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
        this.size = Array(n).fill(1);
    }

    union(a: number, b: number): boolean {
        const pa = this.find(a);
        const pb = this.find(b);
        if (pa === pb) {
            return false;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
        return true;
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }
}

function numSimilarGroups(strs: string[]): number {
    const n = strs.length;
    const m = strs[0].length;
    const uf = new UnionFind(n);
    let cnt = n;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            let diff = 0;
            for (let k = 0; k < m; ++k) {
                if (strs[i][k] !== strs[j][k]) {
                    diff++;
                }
            }
            if (diff <= 2 && uf.union(i, j)) {
                cnt--;
            }
        }
    }
    return cnt;
}
