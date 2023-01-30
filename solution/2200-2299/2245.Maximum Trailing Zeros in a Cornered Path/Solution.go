func maxTrailingZeros(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	r2 := get(m+1, n+1)
	c2 := get(m+1, n+1)
	r5 := get(m+1, n+1)
	c5 := get(m+1, n+1)
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			x := grid[i-1][j-1]
			s2, s5 := 0, 0
			for ; x%2 == 0; x /= 2 {
				s2++
			}
			for ; x%5 == 0; x /= 5 {
				s5++
			}
			r2[i][j] = r2[i][j-1] + s2
			c2[i][j] = c2[i-1][j] + s2
			r5[i][j] = r5[i][j-1] + s5
			c5[i][j] = c5[i-1][j] + s5
		}
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			a := min(r2[i][j]+c2[i-1][j], r5[i][j]+c5[i-1][j])
			b := min(r2[i][j]+c2[m][j]-c2[i][j], r5[i][j]+c5[m][j]-c5[i][j])
			c := min(r2[i][n]-r2[i][j]+c2[i][j], r5[i][n]-r5[i][j]+c5[i][j])
			d := min(r2[i][n]-r2[i][j-1]+c2[m][j]-c2[i][j], r5[i][n]-r5[i][j-1]+c5[m][j]-c5[i][j])
			ans = max(ans, max(a, max(b, max(c, d))))
		}
	}
	return
}

func get(m, n int) [][]int {
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	return f
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}