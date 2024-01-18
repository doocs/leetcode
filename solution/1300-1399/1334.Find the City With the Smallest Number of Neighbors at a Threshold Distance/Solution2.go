func findTheCity(n int, edges [][]int, distanceThreshold int) int {
	g := make([][]int, n)
	const inf int = 1e7
	for i := range g {
		g[i] = make([]int, n)
		for j := range g[i] {
			g[i][j] = inf
		}
	}

	for _, e := range edges {
		f, t, w := e[0], e[1], e[2]
		g[f][t], g[t][f] = w, w
	}

	for k := 0; k < n; k++ {
		g[k][k] = 0
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				g[i][j] = min(g[i][j], g[i][k]+g[k][j])
			}
		}
	}

	ans, cnt := n, n+1
	for i := n - 1; i >= 0; i-- {
		t := 0
		for _, x := range g[i] {
			if x <= distanceThreshold {
				t++
			}
		}
		if t < cnt {
			cnt, ans = t, i
		}
	}

	return ans
}