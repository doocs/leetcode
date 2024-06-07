func hasGroupsSizeX(deck []int) bool {
	cnt := map[int]int{}
	for _, x := range deck {
		cnt[x]++
	}
	g := cnt[deck[0]]
	for _, x := range cnt {
		g = gcd(g, x)
	}
	return g >= 2
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}