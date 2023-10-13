class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    public update(x: number, delta: number) {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    public query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function kEmptySlots(bulbs: number[], k: number): number {
    const n = bulbs.length;
    const tree = new BinaryIndexedTree(n);
    const vis: boolean[] = Array(n + 1).fill(false);
    for (let i = 1; i <= n; ++i) {
        const x = bulbs[i - 1];
        tree.update(x, 1);
        vis[x] = true;
        let y = x - k - 1;
        if (y > 0 && vis[y] && tree.query(x - 1) - tree.query(y) === 0) {
            return i;
        }
        y = x + k + 1;
        if (y <= n && vis[y] && tree.query(y - 1) - tree.query(x) === 0) {
            return i;
        }
    }
    return -1;
}
