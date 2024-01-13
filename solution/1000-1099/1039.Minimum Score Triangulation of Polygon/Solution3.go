func minScoreTriangulation(values []int) int {
	n := len(values)
	f := [50][50]int{}
	for l := 3; l <= n; l++ {
		for i := 0; i+l-1 < n; i++ {
			j := i + l - 1
			f[i][j] = 1 << 30
			for k := i + 1; k < j; k++ {
				f[i][j] = min(f[i][j], f[i][k]+f[k][j]+values[i]*values[k]*values[j])
			}
		}
	}
	return f[0][n-1]
}