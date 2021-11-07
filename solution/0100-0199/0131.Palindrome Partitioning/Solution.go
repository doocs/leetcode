func partition(s string) [][]string {
	n := len(s)
	dp := make([][]bool, n)
	var ans [][]string
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
		for j := 0; j < n; j++ {
			dp[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
		}
	}

	var dfs func(s string, i int, t []string)
	dfs = func(s string, i int, t []string) {
		if i == n {
			ans = append(ans, append([]string(nil), t...))
			return
		}
		for j := i; j < n; j++ {
			if dp[i][j] {
				t = append(t, s[i:j+1])
				dfs(s, j+1, t)
				t = t[:len(t)-1]
			}
		}
	}

	var t []string
	dfs(s, 0, t)
	return ans
}