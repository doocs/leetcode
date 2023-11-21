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

function areConnected(n: number, threshold: number, queries: number[][]): boolean[] {
    const uf = new UnionFind(n + 1);
    for (let a = threshold + 1; a <= n; ++a) {
        for (let b = a * 2; b <= n; b += a) {
            uf.union(a, b);
        }
    }
    return queries.map(([a, b]) => uf.find(a) === uf.find(b));
}
