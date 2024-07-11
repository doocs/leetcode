func isCovered(ranges [][]int, left int, right int) bool {
	diff := [52]int{}
	for _, e := range ranges {
		l, r := e[0], e[1]
		diff[l]++
		diff[r+1]--
	}
	s := 0
	for i, x := range diff {
		s += x
		if s <= 0 && left <= i && i <= right {
			return false
		}
	}
	return true
}