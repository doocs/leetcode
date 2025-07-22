func rowAndMaximumOnes(mat [][]int) []int {
	ans := []int{0, 0}
	for i, row := range mat {
		cnt := 0
		for _, x := range row {
			cnt += x
		}
		if ans[1] < cnt {
			ans = []int{i, cnt}
		}
	}
	return ans
}
