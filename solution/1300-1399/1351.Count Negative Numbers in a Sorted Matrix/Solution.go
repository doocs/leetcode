func countNegatives(grid [][]int) (ans int) {
	m := len(grid)
	n := len(grid[0])
	i := m - 1
	j := 0
	for i >= 0 && j < n {
		if grid[i][j] >= 0 {
			j++
		} else {
			ans += n - j
			i--
		}
	}
	return
}
