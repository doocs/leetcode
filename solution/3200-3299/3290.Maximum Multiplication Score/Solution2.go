func maxScore(a []int, b []int) int64 {
	m, n := len(a), len(b)
	f := make([][]int64, m+1)
	for i := range f {
		f[i] = make([]int64, n+1)
	}
	for i := 0; i < m; i++ {
		f[i][n] = math.MinInt64 / 2
	}
	for j := n - 1; j >= 0; j-- {
		for i := m - 1; i >= 0; i-- {
			f[i][j] = max(f[i][j+1], int64(a[i])*int64(b[j])+f[i+1][j+1])
		}
	}
	return f[0][0]
}
