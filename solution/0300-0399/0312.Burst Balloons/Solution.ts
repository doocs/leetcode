function maxCoins(nums: number[]): number {
    let n = nums.length;
    let dp = Array.from({ length: n + 1 }, v => new Array(n + 2).fill(0));
    nums.unshift(1);
    nums.push(1);
    for (let i = n - 1; i >= 0; --i) {
        for (let j = i + 2; j < n + 2; ++j) {
            for (let k = i + 1; k < j; ++k) {
                dp[i][j] = Math.max(
                    nums[i] * nums[k] * nums[j] + dp[i][k] + dp[k][j],
                    dp[i][j],
                );
            }
        }
    }
    return dp[0][n + 1];
}
