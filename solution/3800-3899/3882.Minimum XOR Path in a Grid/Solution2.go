func minCost(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	mx := 0
	for _, row := range grid {
		for _, x := range row {
			if x > mx {
				mx = x
			}
		}
	}
	mx = 1 << bits.Len(uint(mx))
	f := make([][][]bool, 2)
	for i := range f {
		f[i] = make([][]bool, n)
		for j := range f[i] {
			f[i][j] = make([]bool, mx)
		}
	}
	f[0][0][grid[0][0]] = true
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := grid[i][j]
			if i > 0 || j > 0 {
				for k := 0; k < mx; k++ {
					f[i&1][j][k] = false
					if i > 0 && f[(i-1)&1][j][k^x] {
						f[i&1][j][k] = true
					}
					if j > 0 && f[i&1][j-1][k^x] {
						f[i&1][j][k] = true
					}
				}
			}
		}
	}
	for k := 0; ; k++ {
		if f[(m-1)&1][n-1][k] {
			return k
		}
	}
}
