func minPathCost(grid [][]int, moveCost [][]int) int {
	m, n := len(grid), len(grid[0])
	f := grid[0]
	for i := 1; i < m; i++ {
		g := make([]int, n)
		for j := 0; j < n; j++ {
			g[j] = 1 << 30
			for k := 0; k < n; k++ {
				g[j] = min(g[j], f[k]+moveCost[grid[i-1][k]][j]+grid[i][j])
			}
		}
		f = g
	}
	return slices.Min(f)
}