func maximumPoints(edges [][]int, coins []int, k int) int {
	n := len(coins)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 15)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	var dfs func(int, int, int) int
	dfs = func(i, fa, j int) int {
		if f[i][j] != -1 {
			return f[i][j]
		}
		a := (coins[i] >> j) - k
		b := coins[i] >> (j + 1)
		for _, c := range g[i] {
			if c != fa {
				a += dfs(c, i, j)
				if j < 14 {
					b += dfs(c, i, j+1)
				}
			}
		}
		f[i][j] = max(a, b)
		return f[i][j]
	}
	return dfs(0, -1, 0)
}