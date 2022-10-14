func canCross(stones []int) bool {
	n := len(stones)
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	dp[0][0] = true

	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			k := stones[i] - stones[j]
			// 第 j 个石子上至多只能跳出 j+1 的距离
			if k > j+1 {
				continue
			}
			dp[i][k] = dp[j][k-1] || dp[j][k] || dp[j][k+1]
			if i == n-1 && dp[i][k] {
				return true
			}
		}
	}
	return false
}