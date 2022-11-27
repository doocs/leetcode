func onesMinusZeros(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	rows := make([]int, m)
	cols := make([]int, n)
	diff := make([][]int, m)
	for i, row := range grid {
		diff[i] = make([]int, n)
		for j, v := range row {
			rows[i] += v
			cols[j] += v
		}
	}
	for i, r := range rows {
		for j, c := range cols {
			diff[i][j] = r + c - (n - r) - (m - c)
		}
	}
	return diff
}