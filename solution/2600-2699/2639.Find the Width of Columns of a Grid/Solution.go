func findColumnWidth(grid [][]int) []int {
	n := len(grid[0])
	ans := make([]int, n)
	for _, row := range grid {
		for j, x := range row {
			w := len(strconv.Itoa(x))
			ans[j] = max(ans[j], w)
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