func minReorder(n int, connections [][]int) int {
	type pib struct {
		v int
		b bool
	}
	g := map[int][]pib{}
	for _, e := range connections {
		u, v := e[0], e[1]
		g[u] = append(g[u], pib{v, true})
		g[v] = append(g[v], pib{u, false})
	}
	vis := make([]bool, n)
	var dfs func(int) int
	dfs = func(u int) int {
		ans := 0
		vis[u] = true
		for _, p := range g[u] {
			v, exist := p.v, p.b
			if !vis[v] {
				if exist {
					ans++
				}
				ans += dfs(v)
			}
		}
		return ans
	}
	return dfs(0)
}