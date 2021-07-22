func maxIncreaseKeepingSkyline(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	we := make([]int, m)
	ns := make([]int, n)
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			we[i] = max(we[i], grid[i][j])
			ns[j] = max(ns[j], grid[i][j])
		}
	}
	res := 0
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			res += min(we[i], ns[j]) - grid[i][j]
		}
	}
	return res
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