func equalPairs(grid [][]int) int {
	n := len(grid)
	g := make([][]int, n)
	for i := range g {
		g[i] = make([]int, n)
	}
	for j := 0; j < n; j++ {
		for i := 0; i < n; i++ {
			g[i][j] = grid[j][i]
		}
	}
	ans := 0
	for _, row := range grid {
		for _, col := range g {
			ok := true
			for i, v := range row {
				if v != col[i] {
					ok = false
					break
				}
			}
			if ok {
				ans++
			}
		}
	}
	return ans
}