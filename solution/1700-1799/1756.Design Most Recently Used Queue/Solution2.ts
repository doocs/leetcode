class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    public update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] += v;
            x += x & -x;
        }
    }

    public query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

class MRUQueue {
    private q: number[];
    private tree: BinaryIndexedTree;

    constructor(n: number) {
        this.q = new Array(n + 1);
        for (let i = 1; i <= n; ++i) {
            this.q[i] = i;
        }
        this.tree = new BinaryIndexedTree(n + 2010);
    }

    fetch(k: number): number {
        let l = 1;
        let r = this.q.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (mid - this.tree.query(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        const x = this.q[l];
        this.q.push(x);
        this.tree.update(l, 1);
        return x;
    }
}

/**
 * Your MRUQueue object will be instantiated and called as such:
 * var obj = new MRUQueue(n)
 * var param_1 = obj.fetch(k)
 */
