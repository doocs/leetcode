class BinaryIndexedTree {
    private c: number[];
    private n: number;

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    private static lowbit(x: number): number {
        return x & -x;
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += BinaryIndexedTree.lowbit(x);
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= BinaryIndexedTree.lowbit(x);
        }
        return s;
    }
}

function goodTriplets(nums1: number[], nums2: number[]): number {
    const n = nums1.length;
    const pos = new Map<number, number>();
    nums2.forEach((v, i) => pos.set(v, i + 1));

    const tree = new BinaryIndexedTree(n);
    let ans = 0;

    for (const num of nums1) {
        const p = pos.get(num)!;
        const left = tree.query(p);
        const total = tree.query(n);
        const right = n - p - (total - left);
        ans += left * right;
        tree.update(p, 1);
    }

    return ans;
}
