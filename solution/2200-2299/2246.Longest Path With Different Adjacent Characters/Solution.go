func longestPath(parent []int, s string) int {
	n := len(parent)
	g := make([][]int, n)
	for i := 1; i < n; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	ans := 0
	var dfs func(int) int
	dfs = func(i int) int {
		mx := 0
		for _, j := range g[i] {
			x := dfs(j) + 1
			if s[i] != s[j] {
				ans = max(ans, x+mx)
				mx = max(mx, x)
			}
		}
		return mx
	}
	dfs(0)
	return ans + 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}