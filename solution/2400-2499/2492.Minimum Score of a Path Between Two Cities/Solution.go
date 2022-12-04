func minScore(n int, roads [][]int) int {
	type pair struct{ i, v int }
	g := make([][]pair, n)
	for _, e := range roads {
		a, b, d := e[0]-1, e[1]-1, e[2]
		g[a] = append(g[a], pair{b, d})
		g[b] = append(g[b], pair{a, d})
	}
	vis := make([]bool, n)
	ans := 1 << 30
	var dfs func(int)
	dfs = func(i int) {
		for _, nxt := range g[i] {
			j, d := nxt.i, nxt.v
			ans = min(ans, d)
			if !vis[j] {
				vis[j] = true
				dfs(j)
			}
		}
	}
	dfs(0)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}