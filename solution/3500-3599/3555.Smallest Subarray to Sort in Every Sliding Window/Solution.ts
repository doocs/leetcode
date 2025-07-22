function minSubarraySort(nums: number[], k: number): number[] {
    const inf = Infinity;
    const n = nums.length;
    const f = (i: number, j: number): number => {
        let mi = inf;
        let mx = -inf;
        let l = -1,
            r = -1;
        for (let p = i; p <= j; ++p) {
            if (nums[p] < mx) {
                r = p;
            } else {
                mx = nums[p];
            }
            const q = j - p + i;
            if (nums[q] > mi) {
                l = q;
            } else {
                mi = nums[q];
            }
        }
        return r === -1 ? 0 : r - l + 1;
    };

    const ans: number[] = [];
    for (let i = 0; i <= n - k; ++i) {
        ans.push(f(i, i + k - 1));
    }
    return ans;
}
