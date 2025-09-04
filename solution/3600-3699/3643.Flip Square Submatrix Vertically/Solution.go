func reverseSubmatrix(grid [][]int, x int, y int, k int) [][]int {
	for i := x; i < x+k/2; i++ {
		i2 := x + k - 1 - (i - x)
		for j := y; j < y+k; j++ {
			grid[i][j], grid[i2][j] = grid[i2][j], grid[i][j]
		}
	}
	return grid
}
