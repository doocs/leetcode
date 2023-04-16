func rowAndMaximumOnes(mat [][]int) []int {
	ans := make([]int, 2)
	for i, row := range mat {
		cnt := 0
		for _, x := range row {
			if x == 1 {
				cnt++
			}
		}
		if ans[1] < cnt {
			ans[0], ans[1] = i, cnt
		}
	}
	return ans
}
