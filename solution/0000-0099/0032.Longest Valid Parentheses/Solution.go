func longestValidParentheses(s string) int {
	n := len(s)
	if n < 2 {
		return 0
	}
	dp := make([]int, n)
	ans := 0
	for i := 1; i < n; i++ {
		if s[i] == ')' {
			if s[i-1] == '(' {
				dp[i] = 2
				if i > 1 {
					dp[i] += dp[i-2]
				}
			} else {
				j := i - dp[i-1] - 1
				if j >= 0 && s[j] == '(' {
					dp[i] = 2 + dp[i-1]
					if j > 0 {
						dp[i] += dp[j-1]
					}
				}
			}
			ans = max(ans, dp[i])
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