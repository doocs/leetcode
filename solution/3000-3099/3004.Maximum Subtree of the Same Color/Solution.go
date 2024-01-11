func maximumSubtreeSize(edges [][]int, colors []int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	size := make([]int, n)
	var dfs func(int, int) bool
	dfs = func(a, fa int) bool {
		size[a] = 1
		ok := true
		for _, b := range g[a] {
			if b != fa {
				t := dfs(b, a)
				ok = ok && t && colors[a] == colors[b]
				size[a] += size[b]
			}
		}
		if ok {
			ans = max(ans, size[a])
		}
		return ok
	}
	dfs(0, -1)
	return
}