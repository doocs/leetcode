func minSkips(dist []int, speed int, hoursBefore int) int {
	n := len(dist)
	f := make([][]float64, n+1)
	for i := range f {
		f[i] = make([]float64, n+1)
		for j := range f[i] {
			f[i][j] = 1e20
		}
	}
	f[0][0] = 0
	eps := 1e-8
	for i := 1; i <= n; i++ {
		for j := 0; j <= i; j++ {
			if j < i {
				f[i][j] = math.Min(f[i][j], math.Ceil(f[i-1][j]+float64(dist[i-1])/float64(speed)-eps))
			}
			if j > 0 {
				f[i][j] = math.Min(f[i][j], f[i-1][j-1]+float64(dist[i-1])/float64(speed))
			}
		}
	}
	for j := 0; j <= n; j++ {
		if f[n][j] <= float64(hoursBefore) {
			return j
		}
	}
	return -1
}