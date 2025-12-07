const p: number[] = (() => {
    const res: number[] = [];
    const N = 1 << 14;
    for (let i = 0; i < N; i++) {
        const s = i.toString(2);
        if (s === s.split('').reverse().join('')) {
            res.push(i);
        }
    }
    return res;
})();

function minOperations(nums: number[]): number[] {
    const ans: number[] = Array(nums.length).fill(Number.MAX_SAFE_INTEGER);

    for (let k = 0; k < nums.length; k++) {
        const x = nums[k];
        const i = _.sortedIndex(p, x);
        if (i < p.length) {
            ans[k] = p[i] - x;
        }
        if (i >= 1) {
            ans[k] = Math.min(ans[k], x - p[i - 1]);
        }
    }

    return ans;
}
