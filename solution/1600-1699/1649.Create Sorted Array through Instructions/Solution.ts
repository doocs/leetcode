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

function createSortedArray(instructions: number[]): number {
    const m = Math.max(...instructions);
    const tree = new BinaryIndexedTree(m);
    let ans = 0;
    const mod = 10 ** 9 + 7;
    for (let i = 0; i < instructions.length; ++i) {
        const x = instructions[i];
        const cost = Math.min(tree.query(x - 1), i - tree.query(x));
        ans = (ans + cost) % mod;
        tree.update(x, 1);
    }
    return ans;
}
