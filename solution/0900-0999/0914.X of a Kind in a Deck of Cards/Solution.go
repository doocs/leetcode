func hasGroupsSizeX(deck []int) bool {
	counter := make([]int, 10000)
	for _, d := range deck {
		counter[d]++
	}
	var gcd func(a, b int) int
	gcd = func(a, b int) int {
		if b == 0 {
			return a
		}
		return gcd(b, a%b)
	}
	g := -1
	for _, v := range counter {
		if v > 0 {
			if g == -1 {
				g = v
			} else {
				g = gcd(g, v)
			}
		}
	}
	return g >= 2
}