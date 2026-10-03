func maxOutput(n int, edges [][]int, price []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ a, b int }
	down := make([]pair, n)
	ans := 0
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, [3]int{j, i, 0})
				}
			}
		} else {
			a, b := price[i], 0
			for _, j := range g[i] {
				if j != fa {
					c, d := down[j].a, down[j].b
					ans = max(ans, max(a+d, b+c))
					a = max(a, price[i]+c)
					b = max(b, price[i]+d)
				}
			}
			down[i] = pair{a, b}
		}
	}
	return int64(ans)
}
