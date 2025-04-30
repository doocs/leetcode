func baseUnitConversions(conversions [][]int) []int {
	const mod = int(1e9 + 7)
	n := len(conversions) + 1

	g := make([][]struct{ t, w int }, n)
	for _, e := range conversions {
		s, t, w := e[0], e[1], e[2]
		g[s] = append(g[s], struct{ t, w int }{t, w})
	}

	ans := make([]int, n)

	var dfs func(s int, mul int)
	dfs = func(s int, mul int) {
		ans[s] = mul
		for _, e := range g[s] {
			dfs(e.t, mul*e.w%mod)
		}
	}

	dfs(0, 1)
	return ans
}
