func maximizeSweetness(sweetness []int, k int) int {
	l, r := 0, 0
	for _, v := range sweetness {
		r += v
	}
	check := func(x int) bool {
		s, cnt := 0, 0
		for _, v := range sweetness {
			s += v
			if s >= x {
				s = 0
				cnt++
			}
		}
		return cnt > k
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}