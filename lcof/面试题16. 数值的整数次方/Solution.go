func myPow(x float64, n int) float64 {
	p := abs(n)
	res := 1.0
	for p != 0 {
		if p&1 == 1 {
			res *= x
		}
		x *= x
		p = p >> 1
	}
	if n < 0 {
		return 1 / res
	}
	return res
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}

