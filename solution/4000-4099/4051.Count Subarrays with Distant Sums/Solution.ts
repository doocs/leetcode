class BinaryIndexedTree {
    private readonly n: number;
    private readonly c: number[];

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

function distantSubarrays(nums: number[], goal: number, k: number): number {
    const n = nums.length;
    const s = new Array<number>(n + 1).fill(0);

    for (let i = 0; i < n; i++) {
        s[i + 1] = s[i] + nums[i];
    }

    const st = [...s].sort((a, b) => a - b);

    let ans = (n * (n + 1)) / 2;
    const bit = new BinaryIndexedTree(st.length + 1);

    for (const v of s) {
        const a = v - goal - k + 1;
        const b = v - goal + k - 1;

        const l = _.sortedIndex(st, a) + 1;
        const r = _.sortedIndex(st, b + 1);

        if (l <= r) {
            ans -= bit.query(r) - bit.query(l - 1);
        }

        bit.update(_.sortedIndex(st, v) + 1, 1);
    }

    return ans;
}
