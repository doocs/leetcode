func separateSquares(squares [][]int) float64 {
	s := 0.0
	check := func(y1 float64) bool {
		t := 0.0
		for _, a := range squares {
			y := a[1]
			l := a[2]
			if float64(y) < y1 {
				h := min(float64(l), y1-float64(y))
				t += float64(l) * h
			}
		}
		return t >= s/2.0
	}
	l, r := 0.0, 0.0
	for _, a := range squares {
		s += float64(a[2] * a[2])
		r = max(r, float64(a[1]+a[2]))
	}

	const eps = 1e-5
	for r-l > eps {
		mid := (l + r) / 2.0
		if check(mid) {
			r = mid
		} else {
			l = mid
		}
	}
	return r
}
