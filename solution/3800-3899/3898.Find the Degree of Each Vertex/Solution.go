func findDegrees(matrix [][]int) []int {
	ans := make([]int, len(matrix))
	for i, row := range matrix {
		for _, x := range row {
			ans[i] += x
		}
	}
	return ans
}
