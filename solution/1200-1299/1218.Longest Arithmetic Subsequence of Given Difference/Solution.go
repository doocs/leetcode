func longestSubsequence(arr []int, difference int) int {
	dp, ans := make(map[int]int), 1
	for _, num := range arr {
		dp[num] = dp[num-difference] + 1
		ans = max(ans, dp[num])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
