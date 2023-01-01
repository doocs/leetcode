func equalPairs(grid [][]int) (ans int) {
	n := len(grid)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
		for j := 0; j < n; j++ {
			g[i][j] = grid[j][i]
		}
	}
	for _, row := range grid {
		for _, col := range g {
			ok := 1
			for i, v := range row {
				if v != col[i] {
					ok = 0
					break
				}
			}
			ans += ok
		}
	}
	return
}