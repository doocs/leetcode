class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number) {
        while (x <= this.n) {
            this.c[x] += v;
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

function countRangeSum(nums: number[], lower: number, upper: number): number {
    const n = nums.length;
    const s = Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    let arr: number[] = Array((n + 1) * 3);
    for (let i = 0, j = 0; i <= n; ++i, j += 3) {
        arr[j] = s[i];
        arr[j + 1] = s[i] - lower;
        arr[j + 2] = s[i] - upper;
    }
    arr.sort((a, b) => a - b);
    let m = 0;
    for (let i = 0; i < arr.length; ++i) {
        if (i === 0 || arr[i] !== arr[i - 1]) {
            arr[m++] = arr[i];
        }
    }
    arr = arr.slice(0, m);
    const tree = new BinaryIndexedTree(m);
    let ans = 0;
    for (const x of s) {
        const l = search(arr, m, x - upper);
        const r = search(arr, m, x - lower);
        ans += tree.query(r) - tree.query(l - 1);
        tree.update(search(arr, m, x), 1);
    }
    return ans;
}

function search(nums: number[], r: number, x: number): number {
    let l = 0;
    while (l < r) {
        const mid = (l + r) >> 1;
        if (nums[mid] >= x) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l + 1;
}
