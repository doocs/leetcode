func largestLocal(grid [][]int) [][]int {
	n := len(grid)
	ans := make([][]int, n-2)
	for i := range ans {
		ans[i] = make([]int, n-2)
		for j := 0; j < n-2; j++ {
			for x := i; x <= i+2; x++ {
				for y := j; y <= j+2; y++ {
					ans[i][j] = max(ans[i][j], grid[x][y])
				}
			}
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