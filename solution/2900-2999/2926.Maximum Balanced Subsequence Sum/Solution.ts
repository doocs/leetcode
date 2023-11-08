class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(-Infinity);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = -Infinity;
        while (x > 0) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function maxBalancedSubsequenceSum(nums: number[]): number {
    const n = nums.length;
    const arr = Array(n).fill(0);
    for (let i = 0; i < n; ++i) {
        arr[i] = nums[i] - i;
    }
    arr.sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < n; ++i) {
        if (i === 0 || arr[i] !== arr[i - 1]) {
            arr[m++] = arr[i];
        }
    }
    arr.length = m;
    const tree = new BinaryIndexedTree(m);
    const search = (nums: number[], x: number): number => {
        let [l, r] = [0, nums.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let i = 0; i < n; ++i) {
        const j = search(arr, nums[i] - i) + 1;
        const v = Math.max(tree.query(j), 0) + nums[i];
        tree.update(j, v);
    }
    return tree.query(m);
}
