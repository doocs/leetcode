func probabilityOfHeads(prob []float64, target int) float64 {
	f := make([]float64, target+1)
	f[0] = 1
	for _, p := range prob {
		for j := target; j >= 0; j-- {
			f[j] *= (1 - p)
			if j > 0 {
				f[j] += p * f[j-1]
			}
		}
	}
	return f[target]
}