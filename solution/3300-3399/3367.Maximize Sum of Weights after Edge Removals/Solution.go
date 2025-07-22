func maximizeSumOfWeights(edges [][]int, k int) int64 {
	n := len(edges) + 1
	g := make([][][]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], []int{v, w})
		g[v] = append(g[v], []int{u, w})
	}

	var dfs func(u, fa int) (int64, int64)
	dfs = func(u, fa int) (int64, int64) {
		var s int64
		var t []int64
		for _, e := range g[u] {
			v, w := e[0], e[1]
			if v == fa {
				continue
			}
			a, b := dfs(v, u)
			s += a
			d := int64(w) + b - a
			if d > 0 {
				t = append(t, d)
			}
		}
		sort.Slice(t, func(i, j int) bool {
			return t[i] > t[j]
		})
		for i := 0; i < min(len(t), k-1); i++ {
			s += t[i]
		}
		s2 := s
		if len(t) >= k {
			s += t[k-1]
		}
		return s, s2
	}

	x, y := dfs(0, -1)
	return max(x, y)
}
