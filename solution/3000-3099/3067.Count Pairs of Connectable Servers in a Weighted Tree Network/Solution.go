func countPairsOfConnectableServers(edges [][]int, signalSpeed int) []int {
	n := len(edges) + 1
	type pair struct{ x, w int }
	g := make([][]pair, n)
	for _, e := range edges {
		a, b, w := e[0], e[1], e[2]
		g[a] = append(g[a], pair{b, w})
		g[b] = append(g[b], pair{a, w})
	}
	var dfs func(a, fa, ws int) int
	dfs = func(a, fa, ws int) int {
		cnt := 0
		if ws%signalSpeed == 0 {
			cnt++
		}
		for _, e := range g[a] {
			b, w := e.x, e.w
			if b != fa {
				cnt += dfs(b, a, ws+w)
			}
		}
		return cnt
	}
	ans := make([]int, n)
	for a := 0; a < n; a++ {
		s := 0
		for _, e := range g[a] {
			b, w := e.x, e.w
			t := dfs(b, a, w)
			ans[a] += s * t
			s += t
		}
	}
	return ans
}