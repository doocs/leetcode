function minCost(nums: number[]): number {
    const n = nums.length;
    let ans = Number.MAX_SAFE_INTEGER;
    let l = 0;
    for (let r = 0; r < n; r++) {
        const x = nums[r];
        if (r === n - 1 || x !== nums[r + 1]) {
            if (r > 0 && l < n - 1) {
                ans = Math.min(ans, (l + n - r - 1) * x);
            }
            l = r + 1;
        }
    }
    return ans;
}
