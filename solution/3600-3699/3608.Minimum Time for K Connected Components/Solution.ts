class UnionFind {
    p: number[];
    size: number[];

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
        if (pa === pb) return false;

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

function minTime(n: number, edges: number[][], k: number): number {
    edges.sort((a, b) => a[2] - b[2]);

    const uf = new UnionFind(n);
    let cnt = n;

    for (let i = edges.length - 1; i >= 0; i--) {
        const [u, v, t] = edges[i];

        if (uf.union(u, v)) {
            if (--cnt < k) {
                return t;
            }
        }
    }

    return 0;
}
