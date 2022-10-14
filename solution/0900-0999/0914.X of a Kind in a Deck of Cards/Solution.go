func hasGroupsSizeX(deck []int) bool {
	cnt := make([]int, 10000)
	for _, v := range deck {
		cnt[v]++
	}
	g := -1
	for _, v := range cnt {
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

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}