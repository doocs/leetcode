func champagneTower(poured int, query_row int, query_glass int) float64 {
	g := make([][]float64, 110)
	for i := range g {
		g[i] = make([]float64, 110)
	}
	g[0][0] = float64(poured)
	for i := 0; i <= query_row; i++ {
		for j := 0; j <= i; j++ {
			if g[i][j] > 1 {
				half := (g[i][j] - 1) / 2.0
				g[i][j] = 1
				g[i+1][j] += half
				g[i+1][j+1] += half
			}
		}
	}
	return g[query_row][query_glass]
}