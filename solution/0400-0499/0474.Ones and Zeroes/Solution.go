func findMaxForm(strs []string, m int, n int) int {
	dp := make([][]int, m+1)
	for i := 0; i < m+1; i++ {
		dp[i] = make([]int, n+1)
	}
	for _, s := range strs {
		t := count(s)
		for i := m; i >= t[0]; i-- {
			for j := n; j >= t[1]; j-- {
				dp[i][j] = max(dp[i][j], dp[i-t[0]][j-t[1]]+1)
			}
		}
	}
	return dp[m][n]
}

func count(s string) []int {
	n0 := 0
	for i := 0; i < len(s); i++ {
		if s[i] == '0' {
			n0++
		}
	}
	return []int{n0, len(s) - n0}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}