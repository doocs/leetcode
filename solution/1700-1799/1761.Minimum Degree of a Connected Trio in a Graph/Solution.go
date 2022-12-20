func minTrioDegree(n int, edges [][]int) int {
	g := make([][]bool, n)
	deg := make([]int, n)
	for i := range g {
		g[i] = make([]bool, n)
	}
	for _, e := range edges {
		u, v := e[0]-1, e[1]-1
		g[u][v], g[v][u] = true, true
		deg[u]++
		deg[v]++
	}
	ans := 1 << 30
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			if g[i][j] {
				for k := j + 1; k < n; k++ {
					if g[i][k] && g[j][k] {
						ans = min(ans, deg[i]+deg[j]+deg[k]-6)
					}
				}
			}
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}