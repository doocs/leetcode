function minimumDifference(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = nums[n - 1] - nums[0];
    for (let i = 0; i + k - 1 < n; i++) {
        ans = Math.min(nums[i + k - 1] - nums[i], ans);
    }
    return ans;
}
