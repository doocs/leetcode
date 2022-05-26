func projectionArea(grid [][]int) int {
	xy, yz, zx := 0, 0, 0
	for i, row := range grid {
		maxYz, maxZx := 0, 0
		for j, v := range row {
			if v > 0 {
				xy++
			}
			maxYz = max(maxYz, v)
			maxZx = max(maxZx, grid[j][i])
		}
		yz += maxYz
		zx += maxZx
	}
	return xy + yz + zx
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}