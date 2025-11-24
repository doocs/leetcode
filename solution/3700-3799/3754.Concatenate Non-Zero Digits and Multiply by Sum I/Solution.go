func sumAndMultiply(n int) int64 {
	p := 1
	x := 0
	s := 0
	for n > 0 {
		v := n % 10
		s += v
		if v != 0 {
			x += p * v
			p *= 10
		}
		n /= 10
	}
	return int64(x) * int64(s)
}
