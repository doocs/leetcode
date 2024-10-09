func maxScore(grid [][]int) int {
	m := len(grid)
	mx := 0
	g := [101][11]bool{}
	for i, row := range grid {
		for _, x := range row {
			g[x][i] = true
			mx = max(mx, x)
		}
	}
	f := make([][]int, mx+1)
	for i := range f {
		f[i] = make([]int, 1<<m)
	}
	for i := 1; i <= mx; i++ {
		for j := 0; j < 1<<m; j++ {
			f[i][j] = f[i-1][j]
			for k := 0; k < m; k++ {
				if g[i][k] && (j>>k&1) == 1 {
					f[i][j] = max(f[i][j], f[i-1][j^1<<k]+i)
				}
			}
		}
	}
	return f[mx][1<<m-1]
}
