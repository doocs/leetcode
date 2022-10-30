func averageValue(nums []int) int {
	s, n := 0, 0
	for _, v := range nums {
		if v%6 == 0 {
			s += v
			n++
		}
	}
	if n == 0 {
		return 0
	}
	return s / n
}