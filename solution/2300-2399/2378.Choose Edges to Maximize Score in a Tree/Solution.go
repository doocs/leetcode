func maxScore(edges [][]int) int64 {
	n := len(edges)
	g := make([][][2]int, n)
	for i := 1; i < n; i++ {
		p, w := edges[i][0], edges[i][1]
		g[p] = append(g[p], [2]int{i, w})
	}
	var dfs func(int) [2]int
	dfs = func(i int) [2]int {
		var a, b, t int
		for _, e := range g[i] {
			j, w := e[0], e[1]
			s := dfs(j)
			a += s[1]
			b += s[1]
			t = max(t, s[0]-s[1]+w)
		}
		b += t
		return [2]int{a, b}
	}
	return int64(dfs(0)[1])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}