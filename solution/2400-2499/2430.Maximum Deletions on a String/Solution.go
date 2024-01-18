func deleteString(s string) int {
	n := len(s)
	g := make([][]int, n+1)
	for i := range g {
		g[i] = make([]int, n+1)
	}
	for i := n - 1; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if s[i] == s[j] {
				g[i][j] = g[i+1][j+1] + 1
			}
		}
	}
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if i == n {
			return 0
		}
		if f[i] > 0 {
			return f[i]
		}
		f[i] = 1
		for j := 1; j <= (n-i)/2; j++ {
			if g[i][i+j] >= j {
				f[i] = max(f[i], dfs(i+j)+1)
			}
		}
		return f[i]
	}
	return dfs(0)
}