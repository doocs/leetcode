func isCovered(ranges [][]int, left int, right int) bool {
	diff := make([]int, 52)
	for _, rg := range ranges {
		diff[rg[0]]++
		diff[rg[1]+1]--
	}
	cur := 0
	for i, df := range diff {
		cur += df
		if i >= left && i <= right && cur == 0 {
			return false
		}
	}
	return true
}
