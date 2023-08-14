func mirrorReflection(p int, q int) int {
	g := gcd(p, q)
	p = (p / g) % 2
	q = (q / g) % 2
	if p == 1 && q == 1 {
		return 1
	}
	if p == 1 {
		return 0
	}
	return 2
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}