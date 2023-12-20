class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += v;
        }
    }

    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function subarraysWithMoreZerosThanOnes(nums: number[]): number {
    const n: number = nums.length;
    const base: number = n + 1;
    const tree: BinaryIndexedTree = new BinaryIndexedTree(n + base);
    tree.update(base, 1);
    const mod: number = 1e9 + 7;
    let ans: number = 0;
    let s: number = 0;
    for (const x of nums) {
        s += x === 0 ? -1 : 1;
        ans += tree.query(s - 1 + base);
        ans %= mod;
        tree.update(s + base, 1);
    }
    return ans;
}
