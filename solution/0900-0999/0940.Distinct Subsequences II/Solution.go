func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	dp := make([]int, 26)
	ans := 0
	for _, c := range s {
		c -= 'a'
		add := ans - dp[c] + 1
		ans = (ans + add) % mod
		dp[c] = (dp[c] + add) % mod
	}
	return (ans + mod) % mod
}