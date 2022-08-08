func maxDepthAfterSplit(seq string) []int {
	ans := make([]int, len(seq))
	a, b := 0, 0
	for i, c := range seq {
		if c == '(' {
			if a < b {
				a++
			} else {
				b++
				ans[i] = 1
			}
		} else {
			if a > b {
				a--
			} else {
				b--
				ans[i] = 1
			}
		}
	}
	return ans
}