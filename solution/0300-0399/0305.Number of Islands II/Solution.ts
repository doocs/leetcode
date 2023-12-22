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

function numIslands2(m: number, n: number, positions: number[][]): number[] {
    const grid: number[][] = Array.from({ length: m }, () => Array(n).fill(0));
    const uf = new UnionFind(m * n);
    const ans: number[] = [];
    const dirs: number[] = [-1, 0, 1, 0, -1];
    let cnt = 0;
    for (const [i, j] of positions) {
        if (grid[i][j]) {
            ans.push(cnt);
            continue;
        }
        grid[i][j] = 1;
        ++cnt;
        for (let k = 0; k < 4; ++k) {
            const [x, y] = [i + dirs[k], j + dirs[k + 1]];
            if (x < 0 || x >= m || y < 0 || y >= n || !grid[x][y]) {
                continue;
            }
            if (uf.union(i * n + j, x * n + y)) {
                --cnt;
            }
        }
        ans.push(cnt);
    }
    return ans;
}
