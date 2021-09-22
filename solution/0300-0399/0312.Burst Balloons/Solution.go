func maxCoins(nums []int) int {
	vals := make([]int, len(nums)+2)
	for i := 0; i < len(nums); i++ {
		vals[i+1] = nums[i]
	}
	n := len(vals)
	vals[0], vals[n-1] = 1, 1
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
	}
	for l := 2; l < n; l++ {
		for i := 0; i+l < n; i++ {
			j := i + l
			for k := i + 1; k < j; k++ {
				dp[i][j] = max(dp[i][j], dp[i][k]+dp[k][j]+vals[i]*vals[k]*vals[j])
			}
		}
	}
	return dp[0][n-1]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}