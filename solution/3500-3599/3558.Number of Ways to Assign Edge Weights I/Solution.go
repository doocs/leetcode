func assignEdgeWeights(edges [][]int) int {
	const mod = 1_000_000_007

	n := len(edges) + 1
	g := make([][]int, n+1)

	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	var dfs func(int, int) int
	dfs = func(i, fa int) int {
		res := 0
		for _, j := range g[i] {
			if j != fa {
				res = max(res, dfs(j, i)+1)
			}
		}
		return res
	}

	return pow(2, dfs(1, 0)-1, mod)
}

func pow(a, n, mod int) int {
	res := 1
	for n > 0 {
		if n&1 > 0 {
			res = res * a % mod
		}
		a = a * a % mod
		n >>= 1
	}
	return res
}