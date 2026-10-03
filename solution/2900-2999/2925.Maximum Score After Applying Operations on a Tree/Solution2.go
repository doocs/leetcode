func maximumScoreAfterOperations(edges [][]int, values []int) int64 {
	n := len(values)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ sum, best int64 }
	sub := make([]pair, n)
	type frame struct{ i, fa, state int }
	stk := []frame{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur.i, cur.fa, cur.state
		if state == 0 {
			stk = append(stk, frame{i, fa, 1})
			for _, j := range g[i] {
				if j != fa {
					stk = append(stk, frame{j, i, 0})
				}
			}
		} else {
			var a, b int64
			leaf := true
			for _, j := range g[i] {
				if j != fa {
					leaf = false
					a += sub[j].sum
					b += sub[j].best
				}
			}
			if leaf {
				sub[i] = pair{int64(values[i]), 0}
			} else {
				sub[i] = pair{int64(values[i]) + a, max(int64(values[i])+b, a)}
			}
		}
	}
	return sub[0].best
}
