class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, v: number): void {
        while (x <= this.n) {
            this.c[x] = Math.max(this.c[x], v);
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s = Math.max(s, this.c[x]);
            x -= x & -x;
        }
        return s;
    }
}

function longestObstacleCourseAtEachPosition(obstacles: number[]): number[] {
    const nums: number[] = [...obstacles];
    nums.sort((a, b) => a - b);
    const n: number = nums.length;
    const ans: number[] = [];
    const tree: BinaryIndexedTree = new BinaryIndexedTree(n);
    const search = (x: number): number => {
        let [l, r] = [0, n];
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
    for (let k = 0; k < n; ++k) {
        const i: number = search(obstacles[k]) + 1;
        ans[k] = tree.query(i) + 1;
        tree.update(i, ans[k]);
    }
    return ans;
}
