class UnionFind {
    p: number[];
    size: number[];
    cnt: number;

    constructor(n: number) {
        this.p = Array.from({ length: n }, (_, i) => i);
        this.size = new Array(n).fill(1);
        this.cnt = n;
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

        this.cnt--;
        return true;
    }
}

let N: number;
let E: number[][];
let K: number;

function check(lim: number): boolean {
    const uf = new UnionFind(N);

    for (const [u, v, s] of E) {
        if (s >= lim) {
            uf.union(u, v);
        }
    }

    let rem = K;
    for (const [u, v, s] of E) {
        if (s * 2 >= lim && rem > 0) {
            if (uf.union(u, v)) {
                rem--;
            }
        }
    }

    return uf.cnt === 1;
}

function maxStability(n: number, edges: number[][], k: number): number {
    N = n;
    E = edges;
    K = k;

    const uf = new UnionFind(n);
    let mn = 1e6;

    for (const [u, v, s, must] of edges) {
        if (must) {
            mn = Math.min(mn, s);
            if (!uf.union(u, v)) return -1;
        }
    }

    for (const [u, v] of edges) {
        uf.union(u, v);
    }

    if (uf.cnt > 1) return -1;

    let l = 1,
        r = mn;

    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }

    return l;
}
