func minimumArea(grid [][]int) int {
	x1, y1 := len(grid), len(grid[0])
	x2, y2 := 0, 0
	for i, row := range grid {
		for j, x := range row {
			if x == 1 {
				x1, y1 = min(x1, i), min(y1, j)
				x2, y2 = max(x2, i), max(y2, j)
			}
		}
	}
	return (x2 - x1 + 1) * (y2 - y1 + 1)
}