func uniquePaths(m int, n int) int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	f[0][0] = 1
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i > 0 {
				f[i][j] += f[i-1][j]
			}
			if j > 0 {
				f[i][j] += f[i][j-1]
			}
		}
	}
	return f[m-1][n-1]
}