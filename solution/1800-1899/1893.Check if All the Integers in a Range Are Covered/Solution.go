func isCovered(ranges [][]int, left int, right int) bool {
	diff := [52]int{}
	for _, rg := range ranges {
		l, r := rg[0], rg[1]
		diff[l]++
		diff[r+1]--
	}
	cur := 0
	for i, x := range diff {
		cur += x
		if i >= left && i <= right && cur <= 0 {
			return false
		}
	}
	return true
}