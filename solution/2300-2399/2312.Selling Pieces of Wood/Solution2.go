func sellingWood(m int, n int, prices [][]int) int64 {
	d := make([][]int, m+1)
	f := make([][]int64, m+1)
	for i := range d {
		d[i] = make([]int, n+1)
		f[i] = make([]int64, n+1)
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			f[i][j] = int64(d[i][j])
			for k := 1; k < i; k++ {
				f[i][j] = max(f[i][j], f[k][j]+f[i-k][j])
			}
			for k := 1; k < j; k++ {
				f[i][j] = max(f[i][j], f[i][k]+f[i][j-k])
			}
		}
	}
	return f[m][n]
}