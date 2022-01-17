func countVowelPermutation(n int) int {
	const mod int = 1e9 + 7
	dp := [5]int{1, 1, 1, 1, 1}
	for i := 0; i < n-1; i++ {
		dp = [5]int{
			(dp[1] + dp[2] + dp[4]) % mod,
			(dp[0] + dp[2]) % mod,
			(dp[1] + dp[3]) % mod,
			dp[2],
			(dp[2] + dp[3]) % mod,
		}
	}
	ans := 0
	for _, v := range dp {
		ans = (ans + v) % mod
	}
	return ans
}