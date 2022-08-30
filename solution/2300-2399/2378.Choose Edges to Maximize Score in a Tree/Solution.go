func maxScore(edges [][]int) int64 {
	n := len(edges)
	g := make([]map[int]int, n+1)
	for i := range g {
		g[i] = make(map[int]int)
	}
	for i := 1; i < n; i++ {
		p, w := edges[i][0], edges[i][1]
		g[p][i] = w
	}
	var dfs func(i int) []int
	dfs = func(i int) []int {
		a, b := 0, 0
		s := 0
		for j, v := range g[i] {
			t := dfs(j)
			a += t[1]
			b += t[1]
			s = max(s, t[0]-t[1]+v)
		}
		b += s
		return []int{a, b}
	}
	return int64(dfs(0)[1])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}