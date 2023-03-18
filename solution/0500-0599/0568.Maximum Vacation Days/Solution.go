func maxVacationDays(flights [][]int, days [][]int) (ans int) {
	n, K := len(flights), len(days[0])
	f := make([][]int, K+1)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = -(1 << 30)
		}
	}
	f[0][0] = 0
	for k := 1; k <= K; k++ {
		for j := 0; j < n; j++ {
			f[k][j] = f[k-1][j]
			for i := 0; i < n; i++ {
				if flights[i][j] == 1 {
					f[k][j] = max(f[k][j], f[k-1][i])
				}
			}
			f[k][j] += days[j][k-1]
		}
	}
	for j := 0; j < n; j++ {
		ans = max(ans, f[K][j])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}