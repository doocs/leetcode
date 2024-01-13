function closedIsland(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const uf = new UnionFind(m * n + 1);
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (i === 0 || i === m - 1 || j === 0 || j === n - 1) {
                uf.union(i * n + j, m * n);
            }
            if (grid[i][j] === 0) {
                if (i + 1 < m && grid[i + 1][j] === 0) {
                    uf.union(i * n + j, (i + 1) * n + j);
                }
                if (j + 1 < n && grid[i][j + 1] === 0) {
                    uf.union(i * n + j, i * n + j + 1);
                }
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; j++) {
            if (grid[i][j] === 0 && uf.find(i * n + j) === i * n + j) {
                ++ans;
            }
        }
    }
    return ans;
}

class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array(n)
            .fill(0)
            .map((_, i) => i);
        this.size = Array(n).fill(1);
    }

    find(x: number): number {
        if (this.p[x] !== x) {
            this.p[x] = this.find(this.p[x]);
        }
        return this.p[x];
    }

    union(a: number, b: number): void {
        const [pa, pb] = [this.find(a), this.find(b)];
        if (pa === pb) {
            return;
        }
        if (this.size[pa] > this.size[pb]) {
            this.p[pb] = pa;
            this.size[pa] += this.size[pb];
        } else {
            this.p[pa] = pb;
            this.size[pb] += this.size[pa];
        }
    }
}
