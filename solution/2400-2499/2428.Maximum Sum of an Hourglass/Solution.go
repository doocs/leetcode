func maxSum(grid [][]int) int {
	m, n := len(grid), len(grid[0])
	ans := 0
	for i := 1; i < m-1; i++ {
		for j := 1; j < n-1; j++ {
			t := 0
			for x := i - 1; x <= i+1; x++ {
				for y := j - 1; y <= j+1; y++ {
					t += grid[x][y]
				}
			}
			t -= grid[i][j-1]
			t -= grid[i][j+1]
			ans = max(ans, t)
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