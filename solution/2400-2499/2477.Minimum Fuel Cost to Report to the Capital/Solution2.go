func minimumFuelCost(roads [][]int, seats int) (ans int64) {
	n := len(roads) + 1
	g := make([][]int, n)
	for _, e := range roads {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	sz := make([]int, n)
	for i := range sz {
		sz[i] = 1
	}
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
			for _, b := range g[a] {
				if b != fa {
					t := sz[b]
					ans += int64((t + seats - 1) / seats)
					sz[a] += t
				}
			}
		}
	}
	return
}
