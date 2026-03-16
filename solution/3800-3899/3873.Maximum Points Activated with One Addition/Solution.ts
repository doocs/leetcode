class UnionFind {
    p: Map<number, number> = new Map();
    size: Map<number, number> = new Map();

    find(x: number): number {
        if (!this.p.has(x)) {
            this.p.set(x, x);
            this.size.set(x, 1);
        }
        if (this.p.get(x)! !== x) {
            this.p.set(x, this.find(this.p.get(x)!));
        }
        return this.p.get(x)!;
    }

    union(a: number, b: number): boolean {
        const pa = this.find(a);
        const pb = this.find(b);
        if (pa === pb) return false;

        const sa = this.size.get(pa)!;
        const sb = this.size.get(pb)!;

        if (sa > sb) {
            this.p.set(pb, pa);
            this.size.set(pa, sa + sb);
        } else {
            this.p.set(pa, pb);
            this.size.set(pb, sa + sb);
        }
        return true;
    }
}

function maxActivated(points: number[][]): number {
    const uf = new UnionFind();
    const m = 3e9;

    for (const [x, y] of points) {
        uf.union(x, y + m);
    }

    const cnt = new Map<number, number>();
    for (const [x] of points) {
        const root = uf.find(x);
        cnt.set(root, (cnt.get(root) ?? 0) + 1);
    }

    let mx1 = 0,
        mx2 = 0;
    for (const x of cnt.values()) {
        if (mx1 < x) {
            mx2 = mx1;
            mx1 = x;
        } else if (mx2 < x) {
            mx2 = x;
        }
    }

    return mx1 + mx2 + 1;
}
