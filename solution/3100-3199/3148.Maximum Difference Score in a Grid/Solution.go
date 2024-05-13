func maxScore(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
	}
	const inf int = 1 << 30
	ans := -inf
	for i, row := range grid {
		for j, x := range row {
			mi := inf
			if i > 0 {
				mi = min(mi, f[i-1][j])
			}
			if j > 0 {
				mi = min(mi, f[i][j-1])
			}
			ans = max(ans, x-mi)
			f[i][j] = min(x, mi)
		}
	}
	return ans
}