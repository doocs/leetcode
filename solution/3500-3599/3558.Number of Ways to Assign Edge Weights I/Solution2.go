func assignEdgeWeights(edges [][]int) int {
	const mod = 1_000_000_007

	n := len(edges) + 1
	g := make([][]int, n+1)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}

	d := 0
	stk := [][3]int{{1, 0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, dep := cur[0], cur[1], cur[2]
		d = max(d, dep)
		for _, j := range g[i] {
			if j != fa {
				stk = append(stk, [3]int{j, i, dep + 1})
			}
		}
	}
	return pow(2, d-1, mod)
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
