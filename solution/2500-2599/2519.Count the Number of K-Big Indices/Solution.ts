class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = new Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

function kBigIndices(nums: number[], k: number): number {
    const n = Math.max(...nums);
    const tree1 = new BinaryIndexedTree(n);
    const tree2 = new BinaryIndexedTree(n);

    for (const v of nums) {
        tree2.update(v, 1);
    }

    let ans = 0;
    for (const v of nums) {
        tree2.update(v, -1);
        if (tree1.query(v - 1) >= k && tree2.query(v - 1) >= k) {
            ans++;
        }
        tree1.update(v, 1);
    }

    return ans;
}
