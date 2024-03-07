func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	vis := make([]bool, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	for _, i := range restricted {
		vis[i] = true
	}
	var dfs func(int) int
	dfs = func(i int) (ans int) {
		vis[i] = true
		ans = 1
		for _, j := range g[i] {
			if !vis[j] {
				ans += dfs(j)
			}
		}
		return
	}
	return dfs(0)
}