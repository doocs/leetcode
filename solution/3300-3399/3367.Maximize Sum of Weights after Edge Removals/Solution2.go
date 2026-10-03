func maximizeSumOfWeights(edges [][]int, k int) int64 {
	n := len(edges) + 1
	g := make([][][]int, n)
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		g[u] = append(g[u], []int{v, w})
		g[v] = append(g[v], []int{u, w})
	}
	keep := make([]int64, n)
	reserve := make([]int64, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		u, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{u, fa, 1})
			for _, e := range g[u] {
				if e[0] != fa {
					stk = append(stk, [3]int{e[0], u, 0})
				}
			}
		} else {
			var s int64
			var t []int64
			for _, e := range g[u] {
				v, w := e[0], e[1]
				if v == fa {
					continue
				}
				a, b := keep[v], reserve[v]
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
			reserve[u] = s
			if len(t) >= k {
				s += t[k-1]
			}
			keep[u] = s
		}
	}
	return max(keep[0], reserve[0])
}
