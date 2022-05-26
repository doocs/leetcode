/*
 * @lc app=leetcode.cn id=279 lang=golang
 * 动态规划的思路，状态转移方程：dp[n] = min(dp[n-1*1]+1, dp[n-2*2]+1, ..., dp[n-k*k]+1), ( 0< k*k <=n )
 */
func numSquares(n int) int {
	if n <= 0 {
		return 0
	}
	dp := make([]int, n+1) // 多申请了一份整形，使代码更容易理解, dp[n] 就是 n 的完全平方数的求解
	for i := 1; i <= n; i++ {
		dp[i] = i // 初始值 dp[n] 的最大值的解，也是最容易求的解
		for j := 0; j*j <= i; j++ {
			dp[i] = minInt(dp[i-j*j]+1, dp[i])
		}
	}
	return dp[n]
}

func minInt(x, y int) int {
	if x < y {
		return x
	}
	return y
}