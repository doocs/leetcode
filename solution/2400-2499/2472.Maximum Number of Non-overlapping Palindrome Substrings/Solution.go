func maxPalindromes(s string, k int) int {
	n := len(s)
	dp := make([][]bool, n)
	f := make([]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
		f[i] = -1
		for j := 0; j < n; j++ {
			dp[i][j] = true
		}
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = s[i] == s[j] && dp[i+1][j-1]
		}
	}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		ans := dfs(i + 1)
		for j := i + k - 1; j < n; j++ {
			if dp[i][j] {
				ans = max(ans, 1+dfs(j+1))
			}
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}