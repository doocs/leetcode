class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
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

function countMajoritySubarrays(nums: number[], target: number): number {
    const n = nums.length;
    const tree = new BinaryIndexedTree(2 * n + 1);
    let s = n + 1;
    tree.update(s, 1);
    let ans = 0;
    for (const x of nums) {
        s += x === target ? 1 : -1;
        ans += tree.query(s - 1);
        tree.update(s, 1);
    }
    return ans;
}
