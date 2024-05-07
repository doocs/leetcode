func numberOfRightTriangles(grid [][]int) (ans int64) {
	m, n := len(grid), len(grid[0])
	rows := make([]int, m)
	cols := make([]int, n)
	for i, row := range grid {
		for j, x := range row {
			rows[i] += x
			cols[j] += x
		}
	}
	for i, row := range grid {
		for j, x := range row {
			if x == 1 {
				ans += int64((rows[i] - 1) * (cols[j] - 1))
			}
		}
	}
	return
}