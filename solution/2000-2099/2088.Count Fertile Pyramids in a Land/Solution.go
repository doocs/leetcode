func countPyramids(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	for i := m - 1; i >= 0; i-- {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				f[i][j] = -1
			} else if i == m-1 || j == 0 || j == n-1 {
				f[i][j] = 0
			} else {
				f[i][j] = min(f[i+1][j-1], min(f[i+1][j], f[i+1][j+1])) + 1
				ans += f[i][j]
			}
		}
	}
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if grid[i][j] == 0 {
				f[i][j] = -1
			} else if i == 0 || j == 0 || j == n-1 {
				f[i][j] = 0
			} else {
				f[i][j] = min(f[i-1][j-1], min(f[i-1][j], f[i-1][j+1])) + 1
				ans += f[i][j]
			}
		}
	}
	return
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}