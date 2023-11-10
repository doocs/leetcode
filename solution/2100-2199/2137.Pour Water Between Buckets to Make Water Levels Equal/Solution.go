func equalizeWater(buckets []int, loss int) float64 {
	check := func(v float64) bool {
		var a, b float64
		for _, x := range buckets {
			if float64(x) >= v {
				a += float64(x) - v
			} else {
				b += (v - float64(x)) * 100 / float64(100-loss)
			}
		}
		return a >= b
	}

	l, r := float64(0), float64(slices.Max(buckets))
	for r-l > 1e-5 {
		mid := (l + r) / 2
		if check(mid) {
			l = mid
		} else {
			r = mid
		}
	}
	return l
}