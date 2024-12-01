func maxTargetNodes(edges1 [][]int, edges2 [][]int, k int) []int {
	g2 := build(edges2)
	m := len(edges2) + 1
	t := 0
	for i := 0; i < m; i++ {
		t = max(t, dfs(g2, i, -1, k-1))
	}

	g1 := build(edges1)
	n := len(edges1) + 1
	ans := make([]int, n)
	for i := 0; i < n; i++ {
		ans[i] = t + dfs(g1, i, -1, k)
	}
	return ans
}

func build(edges [][]int) [][]int {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	return g
}

func dfs(g [][]int, a, fa, d int) int {
	if d < 0 {
		return 0
	}
	cnt := 1
	for _, b := range g[a] {
		if b != fa {
			cnt += dfs(g, b, a, d-1)
		}
	}
	return cnt
}
