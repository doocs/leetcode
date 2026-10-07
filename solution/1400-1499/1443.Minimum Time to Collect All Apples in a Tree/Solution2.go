func minTime(n int, edges [][]int, hasApple []bool) int {
	g := make([][]int, n)
	for _, e := range edges {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
		g[v] = append(g[v], u)
	}
	cost := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		u, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{u, fa, 1})
			for _, v := range g[u] {
				if v != fa {
					stk = append(stk, [3]int{v, u, 0})
				}
			}
		} else {
			nxt := 0
			for _, v := range g[u] {
				if v != fa {
					nxt += cost[v]
				}
			}
			if hasApple[u] || nxt > 0 {
				if u == 0 {
					cost[u] = nxt
				} else {
					cost[u] = nxt + 2
				}
			}
		}
	}
	return cost[0]
}
