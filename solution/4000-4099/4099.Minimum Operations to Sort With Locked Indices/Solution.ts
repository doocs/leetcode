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

function minOperations(nums: number[], locked: number[], k: number): number {
    const n = nums.length;
    const bit = new BinaryIndexedTree(n);
    let inv = 0;
    for (let i = n - 1; i >= 0; --i) {
        inv += bit.query(nums[i] - 1);
        bit.update(nums[i], 1);
    }

    let [cnt, mx, j] = [0, 0, 0];
    for (let i = 0; i < n; ++i) {
        mx = Math.max(mx, nums[i]);
        if (mx === i + 1) {
            if (i > j) {
                for (let p = j; p <= i; ++p) {
                    cnt += locked[p];
                }
            }
            j = i + 1;
        }
    }

    return cnt > k ? -1 : cnt + inv;
}
