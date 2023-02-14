func reconstructMatrix(upper int, lower int, colsum []int) [][]int {
	n := len(colsum)
	ans := make([][]int, 2)
	for i := range ans {
		ans[i] = make([]int, n)
	}
	for j, v := range colsum {
		if v == 2 {
			ans[0][j], ans[1][j] = 1, 1
			upper--
			lower--
		}
		if v == 1 {
			if upper > lower {
				upper--
				ans[0][j] = 1
			} else {
				lower--
				ans[1][j] = 1
			}
		}
		if upper < 0 || lower < 0 {
			return [][]int{}
		}
	}
	if upper != 0 || lower != 0 {
		return [][]int{}
	}
	return ans
}