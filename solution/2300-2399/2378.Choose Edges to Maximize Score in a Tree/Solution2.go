func maxScore(edges [][]int) int64 {
	n := len(edges)
	g := make([][][2]int, n)
	for i := 1; i < n; i++ {
		p, w := edges[i][0], edges[i][1]
		g[p] = append(g[p], [2]int{i, w})
	}
	down := make([][2]int, n)
	stk := [][2]int{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, state := cur[0], cur[1]
		if state == 0 {
			stk = append(stk, [2]int{i, 1})
			for _, e := range g[i] {
				stk = append(stk, [2]int{e[0], 0})
			}
		} else {
			var a, b, t int
			for _, e := range g[i] {
				j, w := e[0], e[1]
				x, y := down[j][0], down[j][1]
				a += y
				b += y
				t = max(t, x-y+w)
			}
			b += t
			down[i] = [2]int{a, b}
		}
	}
	return int64(down[0][1])
}
