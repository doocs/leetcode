class PersistentUnionFind {
    private rank: number[];
    private parent: number[];
    private version: number[];

    constructor(n: number) {
        this.rank = Array(n).fill(0);
        this.parent = Array.from({ length: n }, (_, i) => i);
        this.version = Array(n).fill(Infinity);
    }

    find(x: number, t: number): number {
        if (this.parent[x] === x || this.version[x] >= t) {
            return x;
        }
        return this.find(this.parent[x], t);
    }

    union(a: number, b: number, t: number): boolean {
        const pa = this.find(a, Infinity);
        const pb = this.find(b, Infinity);

        if (pa === pb) {
            return false;
        }

        if (this.rank[pa] > this.rank[pb]) {
            this.version[pb] = t;
            this.parent[pb] = pa;
        } else {
            this.version[pa] = t;
            this.parent[pa] = pb;
            if (this.rank[pa] === this.rank[pb]) {
                this.rank[pb]++;
            }
        }

        return true;
    }
}

class DistanceLimitedPathsExist {
    private puf: PersistentUnionFind;

    constructor(n: number, edgeList: number[][]) {
        this.puf = new PersistentUnionFind(n);
        edgeList.sort((a, b) => a[2] - b[2]);
        for (const [u, v, dis] of edgeList) {
            this.puf.union(u, v, dis);
        }
    }

    query(p: number, q: number, limit: number): boolean {
        return this.puf.find(p, limit) === this.puf.find(q, limit);
    }
}

/**
 * Your DistanceLimitedPathsExist object will be instantiated and called as such:
 * var obj = new DistanceLimitedPathsExist(n, edgeList)
 * var param_1 = obj.query(p,q,limit)
 */
