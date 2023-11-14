class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(-1);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let mx = -1;
        while (x > 0) {
            mx = Math.max(mx, this.c[x]);
            x -= x & -x;
        }
        return mx;
    }
}

function maximumSumQueries(nums1: number[], nums2: number[], queries: number[][]): number[] {
    const n = nums1.length;
    const m = queries.length;
    const nums: [number, number][] = [];
    for (let i = 0; i < n; ++i) {
        nums.push([nums1[i], nums2[i]]);
    }
    nums.sort((a, b) => b[0] - a[0]);
    nums2.sort((a, b) => a - b);
    const idx: number[] = Array(m)
        .fill(0)
        .map((_, i) => i);
    idx.sort((i, j) => queries[j][0] - queries[i][0]);
    const ans: number[] = Array(m).fill(0);
    let j = 0;
    const search = (x: number) => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums2[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const tree = new BinaryIndexedTree(n);
    for (const i of idx) {
        const [x, y] = queries[i];
        for (; j < n && nums[j][0] >= x; ++j) {
            const k = n - search(nums[j][1]);
            tree.update(k, nums[j][0] + nums[j][1]);
        }
        const k = n - search(y);
        ans[i] = tree.query(k);
    }
    return ans;
}
