func getMoneyAmount(n int) int {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	for i := n - 1; i > 0; i-- {
		for j := i + 1; j <= n; j++ {
			f[i][j] = j + f[i][j-1]
			for k := i; k < j; k++ {
				f[i][j] = min(f[i][j], k+max(f[i][k-1], f[k+1][j]))
			}
		}
	}
	return f[1][n]
}