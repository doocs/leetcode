func reachableNodes(n int, edges [][]int, restricted []int) int {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	for _, v := range restricted {
		vis[v] = true
	}
	ans := 0
	var dfs func(u int)
	dfs = func(u int) {
		if vis[u] {
			return
		}
		vis[u] = true
		ans++
		for _, v := range g[u] {
			dfs(v)
		}
	}
	dfs(0)
	return ans
}