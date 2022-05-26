func shiftGrid(grid [][]int, k int) [][]int {
	m, n := len(grid), len(grid[0])
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			t := (i*n + j + k) % (m * n)
			ans[t/n][t%n] = grid[i][j]
		}
	}
	return ans
}