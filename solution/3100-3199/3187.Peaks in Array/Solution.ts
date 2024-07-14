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

function countOfPeaks(nums: number[], queries: number[][]): number[] {
    const n = nums.length;
    const tree = new BinaryIndexedTree(n - 1);
    const update = (i: number, val: number): void => {
        if (i <= 0 || i >= n - 1) {
            return;
        }
        if (nums[i - 1] < nums[i] && nums[i] > nums[i + 1]) {
            tree.update(i, val);
        }
    };
    for (let i = 1; i < n - 1; ++i) {
        update(i, 1);
    }
    const ans: number[] = [];
    for (const q of queries) {
        if (q[0] === 1) {
            const [l, r] = [q[1] + 1, q[2] - 1];
            ans.push(l > r ? 0 : tree.query(r) - tree.query(l - 1));
        } else {
            const [idx, val] = [q[1], q[2]];
            for (let i = idx - 1; i <= idx + 1; ++i) {
                update(i, -1);
            }
            nums[idx] = val;
            for (let i = idx - 1; i <= idx + 1; ++i) {
                update(i, 1);
            }
        }
    }
    return ans;
}
