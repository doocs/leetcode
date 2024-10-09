func lastMarkedNodes(edges [][]int) (ans []int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	var dfs func(int, int, []int)
	dfs = func(i, fa int, dist []int) {
		for _, j := range g[i] {
			if j != fa {
				dist[j] = dist[i] + 1
				dfs(j, i, dist)
			}
		}
	}
	maxNode := func(dist []int) int {
		mx := 0
		for i, d := range dist {
			if dist[mx] < d {
				mx = i
			}
		}
		return mx
	}

	dist1 := make([]int, n)
	dfs(0, -1, dist1)
	a := maxNode(dist1)

	dist2 := make([]int, n)
	dfs(a, -1, dist2)
	b := maxNode(dist2)

	dist3 := make([]int, n)
	dfs(b, -1, dist3)

	for i, x := range dist2 {
		if x > dist3[i] {
			ans = append(ans, a)
		} else {
			ans = append(ans, b)
		}
	}
	return
}
