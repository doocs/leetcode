func countGoodNodes(edges [][]int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int, int) int
	dfs = func(a, fa int) int {
		pre, cnt, ok := -1, 1, 1
		for _, b := range g[a] {
			if b != fa {
				cur := dfs(b, a)
				cnt += cur
				if pre < 0 {
					pre = cur
				} else if pre != cur {
					ok = 0
				}
			}
		}
		ans += ok
		return cnt
	}
	dfs(0, -1)
	return
}
