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
}

function maximumMinimumPath(grid: number[][]): number {
    const m = grid.length;
    const n = grid[0].length;
    const q: number[][] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            q.push([grid[i][j], i, j]);
        }
    }
    q.sort((a, b) => b[0] - a[0]);
    const dirs: number[] = [-1, 0, 1, 0, -1];
    const vis: boolean[][] = Array(m)
        .fill(0)
        .map(() => Array(n).fill(false));
    let ans = 0;
    const uf = new UnionFind(m * n);
    for (let k = 0; uf.find(0) !== uf.find(m * n - 1); ++k) {
        const [t, i, j] = q[k];
        ans = t;
        vis[i][j] = true;
        for (let d = 0; d < 4; ++d) {
            const [x, y] = [i + dirs[d], j + dirs[d + 1]];
            if (x >= 0 && x < m && y >= 0 && y < n && vis[x][y]) {
                uf.union(i * n + j, x * n + y);
            }
        }
    }
    return ans;
}
