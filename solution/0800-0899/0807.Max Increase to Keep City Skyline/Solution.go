func maxIncreaseKeepingSkyline(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	rmx := make([]int, m)
	cmx := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			rmx[i] = max(rmx[i], grid[i][j])
			cmx[j] = max(cmx[j], grid[i][j])
		}
	}
	ans := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			ans += min(rmx[i], cmx[j]) - grid[i][j]
		}
	}
	return ans
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