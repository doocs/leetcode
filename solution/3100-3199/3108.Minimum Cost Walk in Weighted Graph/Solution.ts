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

    getSize(x: number): number {
        return this.size[this.find(x)];
    }
}

function minimumCost(n: number, edges: number[][], query: number[][]): number[] {
    const uf = new UnionFind(n);
    const g: number[] = Array(n).fill(-1);
    for (const [u, v, _] of edges) {
        uf.union(u, v);
    }
    for (const [u, _, w] of edges) {
        const root = uf.find(u);
        g[root] &= w;
    }
    const f = (u: number, v: number): number => {
        if (u === v) {
            return 0;
        }
        const [a, b] = [uf.find(u), uf.find(v)];
        return a === b ? g[a] : -1;
    };
    return query.map(([u, v]) => f(u, v));
}
