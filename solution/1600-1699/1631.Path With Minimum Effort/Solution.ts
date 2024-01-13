class UnionFind {
    private p: number[];
    private size: number[];

    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
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

    connected(a: number, b: number): boolean {
        return this.find(a) === this.find(b);
    }
}

function minimumEffortPath(heights: number[][]): number {
    const m = heights.length;
    const n = heights[0].length;
    const uf = new UnionFind(m * n);
    const edges: number[][] = [];
    const dirs = [1, 0, 1];

    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (let k = 0; k < 2; ++k) {
                const x = i + dirs[k];
                const y = j + dirs[k + 1];
                if (x >= 0 && x < m && y >= 0 && y < n) {
                    const d = Math.abs(heights[i][j] - heights[x][y]);
                    edges.push([d, i * n + j, x * n + y]);
                }
            }
        }
    }

    edges.sort((a, b) => a[0] - b[0]);

    for (const [h, a, b] of edges) {
        uf.union(a, b);
        if (uf.connected(0, m * n - 1)) {
            return h;
        }
    }

    return 0;
}
