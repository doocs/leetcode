func checkValidString(s string) bool {
	n := len(s)
	dp := make([][]bool, n)
	for i := range dp {
		dp[i] = make([]bool, n)
		dp[i][i] = s[i] == '*'
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			a, b := s[i], s[j]
			dp[i][j] = (a == '(' || a == '*') && (b == '*' || b == ')') && (i+1 == j || dp[i+1][j-1])
			for k := i; k < j && !dp[i][j]; k++ {
				dp[i][j] = dp[i][k] && dp[k+1][j]
			}
		}
	}
	return dp[0][n-1]
}