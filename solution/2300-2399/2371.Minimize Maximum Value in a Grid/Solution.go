func minScore(grid [][]int) [][]int {
	m, n := len(grid), len(grid[0])
	nums := [][]int{}
	for i, row := range grid {
		for j, v := range row {
			nums = append(nums, []int{v, i, j})
		}
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i][0] < nums[j][0] })
	rowMax := make([]int, m)
	colMax := make([]int, n)
	ans := make([][]int, m)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for _, num := range nums {
		i, j := num[1], num[2]
		ans[i][j] = max(rowMax[i], colMax[j]) + 1
		rowMax[i] = ans[i][j]
		colMax[j] = ans[i][j]
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}