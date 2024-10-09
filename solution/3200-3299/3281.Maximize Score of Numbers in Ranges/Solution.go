func maxPossibleScore(start []int, d int) int {
	check := func(mi int) bool {
		last := math.MinInt64
		for _, st := range start {
			if last+mi > st+d {
				return false
			}
			last = max(st, last+mi)
		}
		return true
	}
	sort.Ints(start)
	l, r := 0, start[len(start)-1]+d-start[0]
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
