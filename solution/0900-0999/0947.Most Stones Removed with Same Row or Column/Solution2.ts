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

function removeStones(stones: number[][]): number {
    const m = 10001;
    const uf = new UnionFind(m << 1);
    for (const [x, y] of stones) {
        uf.union(x, y + m);
    }
    const s = new Set<number>();
    for (const [x, _] of stones) {
        s.add(uf.find(x));
    }
    return stones.length - s.size;
}
