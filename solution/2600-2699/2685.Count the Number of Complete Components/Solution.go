func countCompleteComponents(n int, edges [][]int) (ans int) {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int) (int, int)
	dfs = func(i int) (int, int) {
		vis[i] = true
		x, y := 1, len(g[i])
		for _, j := range g[i] {
			if !vis[j] {
				a, b := dfs(j)
				x += a
				y += b
			}
		}
		return x, y
	}
	for i := range vis {
		if !vis[i] {
			a, b := dfs(i)
			if a*(a-1) == b {
				ans++
			}
		}
	}
	return
}