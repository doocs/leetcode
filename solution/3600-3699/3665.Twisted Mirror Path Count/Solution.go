func uniquePaths(grid [][]int) int {
	const mod = int(1e9 + 7)
	m, n := len(grid), len(grid[0])
	f := make([][][2]int, m)
	for i := range f {
		f[i] = make([][2]int, n)
	}
	for i, row := range grid {
		for j, x := range row {
			r, d := 0, 0
			if j > 0 {
				r = f[i][j-1][0]
			}
			if i > 0 {
				d = f[i-1][j][1]
			}
			if i == 0 && j == 0 {
				d = 1
			}
			if x == 1 {
				f[i][j] = [2]int{d, r}
			} else {
				s := (r + d) % mod
				f[i][j] = [2]int{s, s}
			}
		}
	}
	return f[m-1][n-1][0]
}
