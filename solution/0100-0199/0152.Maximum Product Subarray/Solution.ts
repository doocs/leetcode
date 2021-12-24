function maxProduct(nums: number[]): number {
    let n = nums.length;
    let preMax = nums[0],
        preMin = nums[0],
        ans = nums[0];
    for (let i = 1; i < n; ++i) {
        let cur = nums[i];
        let x = preMax,
            y = preMin;
        preMax = Math.max(x * cur, y * cur, cur);
        preMin = Math.min(x * cur, y * cur, cur);
        ans = Math.max(preMax, ans);
    }
    return ans;
}
