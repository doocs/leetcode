function minimumSumSubarray(nums: number[], l: number, r: number): number {
    const n = nums.length;
    let ans = Infinity;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        for (let j = i; j < n; ++j) {
            s += nums[j];
            const k = j - i + 1;
            if (k >= l && k <= r && s > 0) {
                ans = Math.min(ans, s);
            }
        }
    }
    return ans == Infinity ? -1 : ans;
}
