func maximumPoints(edges [][]int, coins []int, k int) int {
	n := len(coins)
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	f := make([][15]int, n)
	stk := [][3]int{{0, -1, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa, state := cur[0], cur[1], cur[2]
		if state == 0 {
			stk = append(stk, [3]int{i, fa, 1})
			for _, c := range g[i] {
				if c != fa {
					stk = append(stk, [3]int{c, i, 0})
				}
			}
		} else {
			for j := 0; j < 15; j++ {
				a := (coins[i] >> j) - k
				b := coins[i] >> (j + 1)
				for _, c := range g[i] {
					if c != fa {
						a += f[c][j]
						if j < 14 {
							b += f[c][j+1]
						}
					}
				}
				f[i][j] = max(a, b)
			}
		}
	}
	return f[0][0]
}
