func gardenNoAdj(n int, paths [][]int) []int {
	g := make([][]int, n)
	for _, p := range paths {
		x, y := p[0]-1, p[1]-1
		g[x] = append(g[x], y)
		g[y] = append(g[y], x)
	}
	ans := make([]int, n)
	for u := 0; u < n; u++ {
		colors := make(map[int]bool)
		for _, v := range g[u] {
			colors[ans[v]] = true
		}
		for c := 1; c < 5; c++ {
			if !colors[c] {
				ans[u] = c
				break
			}
		}
	}
	return ans
}