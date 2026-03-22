func maxProductPath(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][][2]int64, m)
	for i := range f {
		f[i] = make([][2]int64, n)
	}

	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			x := int64(grid[i][j])
			if i == 0 && j == 0 {
				f[i][j] = [2]int64{x, x}
				continue
			}

			mn, mx := int64(1<<63-1), int64(-1<<63)

			if i > 0 {
				a, b := f[i-1][j][0], f[i-1][j][1]
				mn = min(mn, min(a*x, b*x))
				mx = max(mx, max(a*x, b*x))
			}

			if j > 0 {
				a, b := f[i][j-1][0], f[i][j-1][1]
				mn = min(mn, min(a*x, b*x))
				mx = max(mx, max(a*x, b*x))
			}

			f[i][j] = [2]int64{mn, mx}
		}
	}

	ans := f[m-1][n-1][1]
	mod := int64(1e9 + 7)
	if ans < 0 {
		return -1
	}
	return int(ans % mod)
}
