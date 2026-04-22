class BIT {
    private c: Int32Array;
    constructor(private n: number) {
        this.c = new Int32Array(n + 1);
    }
    update(x: number, delta: number) {
        for (; x <= this.n; x += x & -x) this.c[x] += delta;
    }
    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) s += this.c[x];
        return s;
    }
}

function countSmallerOppositeParity(nums: number[]): number[] {
    const n = nums.length;
    const sorted = _.sortedUniq(_.sortBy(nums));
    const m = sorted.length;

    const bits = [new BIT(m), new BIT(m)];
    const ans = new Array(n);

    for (let i = n - 1; i >= 0; i--) {
        const rank = _.sortedIndex(sorted, nums[i]) + 1;
        ans[i] = bits[(nums[i] & 1) ^ 1].query(rank - 1);
        bits[nums[i] & 1].update(rank, 1);
    }
    return ans;
}
