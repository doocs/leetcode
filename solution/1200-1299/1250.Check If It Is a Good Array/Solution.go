func isGoodArray(nums []int) bool {
	g := 0
	for _, x := range nums {
		g = gcd(x, g)
	}
	return g == 1
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}