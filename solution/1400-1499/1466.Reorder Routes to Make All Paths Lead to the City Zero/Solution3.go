func minReorder(n int, connections [][]int) (ans int) {
	g := make([][][2]int, n)
	for _, e := range connections {
		a, b := e[0], e[1]
		g[a] = append(g[a], [2]int{b, 1})
		g[b] = append(g[b], [2]int{a, 0})
	}
	stk := [][2]int{{0, -1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		a, fa := cur[0], cur[1]
		for _, e := range g[a] {
			b, c := e[0], e[1]
			if b != fa {
				ans += c
				stk = append(stk, [2]int{b, a})
			}
		}
	}
	return
}
