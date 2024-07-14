func dicesProbability(n int) (ans []float64) {
	f := make([]int, 7)
	for i := 1; i <= 6; i++ {
		f[i] = 1
	}

	for i := 2; i <= n; i++ {
		g := make([]int, 6*i+1)
		for j := i; j <= 6*i; j++ {
			for k := 1; k <= 6; k++ {
				if j-k >= 0 && j-k < len(f) {
					g[j] += f[j-k]
				}
			}
		}
		f = g
	}

	m := math.Pow(6, float64(n))
	for j := n; j <= 6*n; j++ {
		ans = append(ans, float64(f[j])/m)
	}
	return
}