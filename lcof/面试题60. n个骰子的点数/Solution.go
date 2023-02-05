func dicesProbability(n int) (ans []float64) {
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 6*n+1)
	}
	for j := 1; j <= 6; j++ {
		f[1][j] = 1
	}
	for i := 2; i <= n; i++ {
		for j := i; j <= 6*i; j++ {
			for k := 1; k <= 6; k++ {
				if j >= k {
					f[i][j] += f[i-1][j-k]
				}
			}
		}
	}
	m := math.Pow(6, float64(n))
	for j := n; j <= 6*n; j++ {
		ans = append(ans, float64(f[n][j])/m)
	}
	return
}