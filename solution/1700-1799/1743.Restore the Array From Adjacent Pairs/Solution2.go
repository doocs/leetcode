func restoreArray(adjacentPairs [][]int) []int {
	g := map[int][]int{}
	for _, e := range adjacentPairs {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := []int{}
	var dfs func(i, fa int)
	dfs = func(i, fa int) {
		ans = append(ans, i)
		for _, j := range g[i] {
			if j != fa {
				dfs(j, i)
			}
		}
	}
	for i, v := range g {
		if len(v) == 1 {
			dfs(i, 1000000)
			break
		}
	}
	return ans
}