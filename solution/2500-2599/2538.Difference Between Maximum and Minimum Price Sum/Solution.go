func maxOutput(n int, edges [][]int, price []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	type pair struct{ a, b int }
	ans := 0
	var dfs func(i, fa int) pair
	dfs = func(i, fa int) pair {
		a, b := price[i], 0
		for _, j := range g[i] {
			if j != fa {
				e := dfs(j, i)
				c, d := e.a, e.b
				ans = max(ans, max(a+d, b+c))
				a = max(a, price[i]+c)
				b = max(b, price[i]+d)
			}
		}
		return pair{a, b}
	}
	dfs(0, -1)
	return int64(ans)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}