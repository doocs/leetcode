func cherryPickup(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	f := make([][][]int, m)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
			for k := range f[i][j] {
				f[i][j][k] = -1
			}
		}
	}
	f[0][0][n-1] = grid[0][0] + grid[0][n-1]
	for i := 1; i < m; i++ {
		for j1 := 0; j1 < n; j1++ {
			for j2 := 0; j2 < n; j2++ {
				x := grid[i][j1]
				if j1 != j2 {
					x += grid[i][j2]
				}
				for y1 := j1 - 1; y1 <= j1+1; y1++ {
					for y2 := j2 - 1; y2 <= j2+1; y2++ {
						if y1 >= 0 && y1 < n && y2 >= 0 && y2 < n && f[i-1][y1][y2] != -1 {
							f[i][j1][j2] = max(f[i][j1][j2], f[i-1][y1][y2]+x)
						}
					}
				}
			}
		}
	}
	for j1 := 0; j1 < n; j1++ {
		ans = max(ans, slices.Max(f[m-1][j1]))
	}
	return
}