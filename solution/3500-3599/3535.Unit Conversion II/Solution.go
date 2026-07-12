func queryConversions(conversions [][]int, queries [][]int) []int {
	const mod = int(1e9 + 7)
	n := len(conversions) + 1

	g := make([][]struct{ t, w int }, n)
	for _, e := range conversions {
		s, t, w := e[0], e[1], e[2]
		g[s] = append(g[s], struct{ t, w int }{t, w})
	}

	res := make([]int, n)

	var dfs func(int, int)
	dfs = func(s, mul int) {
		res[s] = mul
		for _, e := range g[s] {
			dfs(e.t, mul*e.w%mod)
		}
	}
	dfs(0, 1)

	qpow := func(x, n int) int {
		res := 1
		for n > 0 {
			if n&1 > 0 {
				res = res * x % mod
			}
			x = x * x % mod
			n >>= 1
		}
		return res
	}

	ans := make([]int, len(queries))
	for i, q := range queries {
		ans[i] = res[q[1]] * qpow(res[q[0]], mod-2) % mod
	}
	return ans
}
