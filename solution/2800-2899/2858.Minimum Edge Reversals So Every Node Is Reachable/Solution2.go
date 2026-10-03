func minEdgeReversals(n int, edges [][]int) []int {
	g := make([][][2]int, n)
	for _, e := range edges {
		x, y := e[0], e[1]
		g[x] = append(g[x], [2]int{y, 1})
		g[y] = append(g[y], [2]int{x, -1})
	}
	ans := make([]int, n)
	stk := [][2]int{{0, -1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa := cur[0], cur[1]
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				if k < 0 {
					ans[0]++
				}
				stk = append(stk, [2]int{j, i})
			}
		}
	}
	stk = append(stk, [2]int{0, -1})
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, fa := cur[0], cur[1]
		for _, ne := range g[i] {
			j, k := ne[0], ne[1]
			if j != fa {
				ans[j] = ans[i] + k
				stk = append(stk, [2]int{j, i})
			}
		}
	}
	return ans
}
