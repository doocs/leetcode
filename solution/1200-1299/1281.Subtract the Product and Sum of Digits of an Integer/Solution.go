func subtractProductAndSum(n int) int {
	s, p := 0, 1
	for n != 0 {
		t := n % 10
		n /= 10
		s += t
		p *= t
	}
	return p - s
}