function rob(nums: number[]): number {
    const n = nums.length;
    if (n === 1) {
        return nums[0];
    }
    const robRange = (left: number, right: number) => {
        const dp = [0, 0];
        for (let i = left; i < right; i++) {
            [dp[0], dp[1]] = [dp[1], Math.max(dp[1], dp[0] + nums[i])];
        }
        return dp[1];
    };
    return Math.max(robRange(0, n - 1), robRange(1, n));
}
