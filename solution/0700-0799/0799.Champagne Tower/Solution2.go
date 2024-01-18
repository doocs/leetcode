func champagneTower(poured int, query_row int, query_glass int) float64 {
	f := []float64{float64(poured)}
	for i := 1; i <= query_row; i++ {
		g := make([]float64, i+1)
		for j, v := range f {
			if v > 1 {
				half := (v - 1) / 2.0
				g[j] += half
				g[j+1] += half
			}
		}
		f = g
	}
	return math.Min(1, f[query_glass])
}