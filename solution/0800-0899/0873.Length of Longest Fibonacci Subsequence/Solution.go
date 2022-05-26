func lenLongestFibSubseq(arr []int) int {
	n := len(arr)
	mp := make(map[int]int, n)
	for i, v := range arr {
		mp[v] = i + 1
	}
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
		for j := 0; j < i; j++ {
			dp[j][i] = 2
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		for j := 0; j < i; j++ {
			delta := arr[i] - arr[j]
			k := mp[delta] - 1
			if k >= 0 && k < j {
				dp[j][i] = dp[k][j] + 1
				ans = max(ans, dp[j][i])
			}
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}