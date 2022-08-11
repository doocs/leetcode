func surfaceArea(grid [][]int) int {
	ans := 0
	for i, row := range grid {
		for j, v := range row {
			if v > 0 {
				ans += 2 + v*4
				if i > 0 {
					ans -= min(v, grid[i-1][j]) * 2
				}
				if j > 0 {
					ans -= min(v, grid[i][j-1]) * 2
				}
			}
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}