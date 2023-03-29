func maxSum(grid [][]int) (ans int) {
	m, n := len(grid), len(grid[0])
	for i := 1; i < m-1; i++ {
		for j := 1; j < n-1; j++ {
			s := -grid[i][j-1] - grid[i][j+1]
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					s += grid[x][y]
				}
			}
			ans = max(ans, s)
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}