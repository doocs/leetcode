func minimumFuelCost(roads [][]int, seats int) (ans int64) {
	n := len(roads) + 1
	g := make([][]int, n)
	for _, e := range roads {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int, int) int
	dfs = func(a, fa int) int {
		sz := 1
		for _, b := range g[a] {
			if b != fa {
				t := dfs(b, a)
				ans += int64((t + seats - 1) / seats)
				sz += t
			}
		}
		return sz
	}
	dfs(0, -1)
	return
}