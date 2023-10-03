func maxKDivisibleComponents(n int, edges [][]int, values []int, k int) (ans int) {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int, int) int
	dfs = func(i, fa int) int {
		s := values[i]
		for _, j := range g[i] {
			if j != fa {
				s += dfs(j, i)
			}
		}
		if s%k == 0 {
			ans++
		}
		return s
	}
	dfs(0, -1)
	return
}