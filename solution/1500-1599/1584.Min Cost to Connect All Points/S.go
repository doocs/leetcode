func minCostConnectPoints(points [][]int) int {
	n := len(points)
	inf := 0x3f3f3f3f
	g := make([][]int, n)
	dist := make([]int, n)
	vis := make([]bool, n)
	for i, p1 := range points {
		dist[i] = inf
		g[i] = make([]int, n)
		for j, p2 := range points {
			if i != j {
				x1, y1 := p1[0], p1[1]
				x2, y2 := p2[0], p2[1]
				g[i][j] = abs(x1-x2) + abs(y1-y2)
			}
		}
	}
	ans := 0
	for i := 0; i < n; i++ {
		t := -1
		for j := 0; j < n; j++ {
			if !vis[j] && (t == -1 || dist[t] > dist[j]) {
				t = j
			}
		}
		if i > 0 {
			ans += dist[t]
		}
		for j := 0; j < n; j++ {
			dist[j] = min(dist[j], g[t][j])
		}
		vis[t] = true
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}