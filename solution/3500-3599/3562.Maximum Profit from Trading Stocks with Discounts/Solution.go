func maxProfit(n int, present []int, future []int, hierarchy [][]int, budget int) int {
	g := make([][]int, n+1)
	for _, e := range hierarchy {
		u, v := e[0], e[1]
		g[u] = append(g[u], v)
	}

	var dfs func(u int) [][2]int
	dfs = func(u int) [][2]int {
		nxt := make([][2]int, budget+1)

		for _, v := range g[u] {
			fv := dfs(v)
			for j := budget; j >= 0; j-- {
				for jv := 0; jv <= j; jv++ {
					for pre := 0; pre < 2; pre++ {
						nxt[j][pre] = max(nxt[j][pre], nxt[j-jv][pre]+fv[jv][pre])
					}
				}
			}
		}

		f := make([][2]int, budget+1)
		price := future[u-1]

		for j := 0; j <= budget; j++ {
			for pre := 0; pre < 2; pre++ {
				cost := present[u-1] / (pre + 1)
				if j >= cost {
					buyProfit := nxt[j-cost][1] + (price - cost)
					f[j][pre] = max(nxt[j][0], buyProfit)
				} else {
					f[j][pre] = nxt[j][0]
				}
			}
		}
		return f
	}

	return dfs(1)[budget][0]
}
