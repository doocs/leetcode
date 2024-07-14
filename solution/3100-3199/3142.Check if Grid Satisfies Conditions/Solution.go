func satisfiesConditions(grid [][]int) bool {
	m, n := len(grid), len(grid[0])
	for i, row := range grid {
		for j, x := range row {
			if i+1 < m && x != grid[i+1][j] {
				return false
			}
			if j+1 < n && x == grid[i][j+1] {
				return false
			}
		}
	}
	return true
}