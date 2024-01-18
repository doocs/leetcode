func commonFactors(a int, b int) (ans int) {
	g := gcd(a, b)
	for x := 1; x <= g; x++ {
		if g%x == 0 {
			ans++
		}
	}
	return
}

func gcd(a int, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}