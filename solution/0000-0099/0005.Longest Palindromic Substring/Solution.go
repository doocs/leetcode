func longestPalindrome(s string) string {
	n := len(s)
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	mx, start := 1, 0
	for j := 0; j < n; j++ {
		for i := 0; i <= j; i++ {
			if j-i < 2 {
				dp[i][j] = s[i] == s[j]
			} else {
				dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
			}
			if dp[i][j] && mx < j-i+1 {
				mx, start = j-i+1, i
			}
		}
	}
	return s[start : start+mx]
}