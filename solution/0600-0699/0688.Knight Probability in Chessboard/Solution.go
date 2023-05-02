func knightProbability(n int, k int, row int, column int) float64 {
	f := make([][][]float64, k+1)
	for h := range f {
		f[h] = make([][]float64, n)
		for i := range f[h] {
			f[h][i] = make([]float64, n)
			for j := range f[h][i] {
				f[0][i][j] = 1
			}
		}
	}
	dirs := [9]int{-2, -1, 2, 1, -2, 1, 2, -1, -2}
	for h := 1; h <= k; h++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				for p := 0; p < 8; p++ {
					x, y := i+dirs[p], j+dirs[p+1]
					if x >= 0 && x < n && y >= 0 && y < n {
						f[h][i][j] += f[h-1][x][y] / 8
					}
				}
			}
		}
	}
	return f[k][row][column]
}