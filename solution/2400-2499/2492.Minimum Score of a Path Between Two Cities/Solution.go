func minScore(n int, roads [][]int) int {
	g := make([][][2]int, n+1)
	for _, e := range roads {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], [2]int{b, w})
		g[b] = append(g[b], [2]int{a, w})
	}

	vis := make([]bool, n+1)
	ans := int(1e9)

	var dfs func(int)
	dfs = func(a int) {
		vis[a] = true
		for _, nb := range g[a] {
			b, w := nb[0], nb[1]
			ans = min(ans, w)
			if !vis[b] {
				dfs(b)
			}
		}
	}

	dfs(1)
	return ans
}