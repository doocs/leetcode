func countGoodNodes(edges [][]int) (ans int) {
	n := len(edges) + 1
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	sz := make([]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		a, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{a, fa, 1})
			for _, b := range g[a] {
				if b != fa {
					stk = append(stk, [3]int{b, a, 0})
				}
			}
		} else {
			pre, cnt, ok := -1, 1, 1
			for _, b := range g[a] {
				if b != fa {
					curSz := sz[b]
					cnt += curSz
					if pre < 0 {
						pre = curSz
					} else if pre != curSz {
						ok = 0
					}
				}
			}
			ans += ok
			sz[a] = cnt
		}
	}
	return
}
