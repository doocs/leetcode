func preimageSizeFZF(k int) int {
	f := func(x int) int {
		res := 0
		for x != 0 {
			x /= 5
			res += x
		}
		return res
	}

	g := func(k int) int {
		left, right := 0, k*5
		for left < right {
			mid := (left + right) >> 1
			if f(mid) >= k {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}

	return g(k+1) - g(k)
}