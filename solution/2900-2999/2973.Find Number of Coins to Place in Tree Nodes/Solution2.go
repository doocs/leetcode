func placedCoins(edges [][]int, cost []int) []int64 {
	n := len(cost)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := make([]int64, n)
	for i := range ans {
		ans[i] = 1
	}
	sub := make([][]int, n)
	type frame struct{ a, fa, state int }
	stk := []frame{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		a, fa, state := cur.a, cur.fa, cur.state
		if state == 0 {
			stk = append(stk, frame{a, fa, 1})
			for _, b := range g[a] {
				if b != fa {
					stk = append(stk, frame{b, a, 0})
				}
			}
		} else {
			res := []int{cost[a]}
			for _, b := range g[a] {
				if b != fa {
					res = append(res, sub[b]...)
				}
			}
			sort.Ints(res)
			m := len(res)
			if m >= 3 {
				x := int64(res[m-1]) * int64(res[m-2]) * int64(res[m-3])
				y := int64(res[0]) * int64(res[1]) * int64(res[m-1])
				ans[a] = max(x, y, int64(0))
			}
			if m > 5 {
				res = []int{res[0], res[1], res[m-3], res[m-2], res[m-1]}
			}
			sub[a] = res
		}
	}
	return ans
}
