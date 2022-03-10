func stoneGameIX(stones []int) bool {
	check := func(c [3]int) bool {
		if c[1] == 0 {
			return false
		}
		c[1]--
		turn := 1 + min(c[1], c[2])*2 + c[0]
		if c[1] > c[2] {
			c[1]--
			turn++
		}
		return turn%2 == 1 && c[1] != c[2]
	}
	c := [3]int{}
	for _, s := range stones {
		c[s%3]++
	}
	return check(c) || check([3]int{c[0], c[2], c[1]})
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}