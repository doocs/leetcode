func minCostConnectPoints(points [][]int) (ans int) {
	n := len(points)
	g := make([][]int, n)
	vis := make([]bool, n)
	dist := make([]int, n)
	for i := range g {
		g[i] = make([]int, n)
		dist[i] = 1 << 30
	}
	for i := range g {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			t := abs(x1-x2) + abs(y1-y2)
			g[i][j] = t
			g[j][i] = t
		}
	}
	dist[0] = 0
	for i := 0; i < n; i++ {
		j := -1
		for k := 0; k < n; k++ {
			if !vis[k] && (j == -1 || dist[k] < dist[j]) {
				j = k
			}
		}
		vis[j] = true
		ans += dist[j]
		for k := 0; k < n; k++ {
			if !vis[k] {
				dist[k] = min(dist[k], g[j][k])
			}
		}
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}