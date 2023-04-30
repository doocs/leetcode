class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
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

function countOperationsToEmptyArray(nums: number[]): number {
    const pos: Map<number, number> = new Map();
    const n = nums.length;
    for (let i = 0; i < n; ++i) {
        pos.set(nums[i], i);
    }
    nums.sort((a, b) => a - b);
    const tree = new BinaryIndexedTree(n);
    let ans = pos.get(nums[0])! + 1;
    for (let k = 0; k < n - 1; ++k) {
        const i = pos.get(nums[k])!;
        const j = pos.get(nums[k + 1])!;
        let d = j - i - (tree.query(j + 1) - tree.query(i + 1));
        if (i > j) {
            d += n - k;
        }
        ans += d;
        tree.update(i + 1, 1);
    }
    return ans;
}
