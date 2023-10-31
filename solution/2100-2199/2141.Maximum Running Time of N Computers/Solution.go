func maxRunTime(n int, batteries []int) int64 {
	l, r := 0, 0
	for _, x := range batteries {
		r += x
	}
	for l < r {
		mid := (l + r + 1) >> 1
		s := 0
		for _, x := range batteries {
			s += min(x, mid)
		}
		if s >= n*mid {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return int64(l)
}