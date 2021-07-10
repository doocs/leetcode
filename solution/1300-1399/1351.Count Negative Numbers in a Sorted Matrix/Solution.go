func countNegatives(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	i, j, cnt := 0, n-1, 0
	for i < m && j >= 0 {
		if grid[i][j] < 0 {
			cnt += (m - i)
			j--
		} else {
			i++
		}
	}
	return cnt
}