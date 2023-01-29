func countPairs(n int, edges [][]int) (ans int64) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	vis := make([]bool, n)
	var dfs func(int) int
	dfs = func(i int) int {
		vis[i] = true
		cnt := 1
		for _, j := range g[i] {
			if !vis[j] {
				cnt += dfs(j)
			}
		}
		return cnt
	}
	var s int64
	for i := 0; i < n; i++ {
		if !vis[i] {
			t := int64(dfs(i))
			ans += s * t
			s += t
		}
	}
	return
}