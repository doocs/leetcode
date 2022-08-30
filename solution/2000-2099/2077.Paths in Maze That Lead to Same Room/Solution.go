func numberOfPaths(n int, corridors [][]int) int {
	g := make([]map[int]bool, n+1)
	for i := range g {
		g[i] = make(map[int]bool)
	}
	for _, c := range corridors {
		a, b := c[0], c[1]
		g[a][b] = true
		g[b][a] = true
	}
	ans := 0
	for c := 1; c <= n; c++ {
		nxt := []int{}
		for v := range g[c] {
			nxt = append(nxt, v)
		}
		m := len(nxt)
		for i := 0; i < m; i++ {
			for j := i + 1; j < m; j++ {
				a, b := nxt[i], nxt[j]
				if g[b][a] {
					ans++
				}
			}
		}
	}
	return ans / 3
}