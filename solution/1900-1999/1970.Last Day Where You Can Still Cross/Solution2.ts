class UnionFind {
    p: number[];
    size: number[];
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
        const [pa, pb] = [this.find(a), this.find(b)];
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

function latestDayToCross(row: number, col: number, cells: number[][]): number {
    const mn = cells.length;
    const uf = new UnionFind(row * col + 2);
    const [s, t] = [mn, mn + 1];
    const g: number[][] = Array.from({ length: row }, () => Array(col).fill(1));
    const dirs: number[] = [-1, 0, 1, 0, -1];
    for (let i = mn - 1; ; --i) {
        const [x, y] = [cells[i][0] - 1, cells[i][1] - 1];
        g[x][y] = 0;
        for (let j = 0; j < 4; ++j) {
            const [nx, ny] = [x + dirs[j], y + dirs[j + 1]];
            if (nx >= 0 && nx < row && ny >= 0 && ny < col && g[nx][ny] === 0) {
                uf.union(x * col + y, nx * col + ny);
            }
        }
        if (x === 0) {
            uf.union(s, y);
        }
        if (x === row - 1) {
            uf.union(t, x * col + y);
        }
        if (uf.find(s) === uf.find(t)) {
            return i;
        }
    }
}
