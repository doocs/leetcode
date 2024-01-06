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
		ans[i] = int64(1)
	}
	var dfs func(a, fa int) []int
	dfs = func(a, fa int) []int {
		res := []int{cost[a]}
		for _, b := range g[a] {
			if b != fa {
				res = append(res, dfs(b, a)...)
			}
		}
		sort.Ints(res)
		m := len(res)
		if m >= 3 {
			x := res[m-1] * res[m-2] * res[m-3]
			y := res[0] * res[1] * res[m-1]
			ans[a] = max(0, int64(x), int64(y))
		}
		if m >= 5 {
			res = append(res[:2], res[m-3:]...)
		}
		return res
	}
	dfs(0, -1)
	return ans
}