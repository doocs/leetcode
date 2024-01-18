func maxValue(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = max(f[i-1][j], f[i][j-1]) + grid[i-1][j-1]
		}
	}
	return f[m][n]
}