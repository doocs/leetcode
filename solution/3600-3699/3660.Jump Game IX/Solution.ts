function maxValue(nums: number[]): number[] {
    const n = nums.length;
    const ans = Array(n).fill(0);
    const preMax = Array(n).fill(nums[0]);
    for (let i = 1; i < n; i++) {
        preMax[i] = Math.max(preMax[i - 1], nums[i]);
    }
    let sufMin = 1 << 30;
    for (let i = n - 1; i >= 0; i--) {
        ans[i] = preMax[i] > sufMin ? ans[i + 1] : preMax[i];
        sufMin = Math.min(sufMin, nums[i]);
    }
    return ans;
}
