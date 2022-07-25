func numMovesStones(a int, b int, c int) []int {
	x := min(min(a, b), c)
	z := max(max(a, b), c)
	y := a + b + c - x - z
	if z-x == 2 {
		return []int{0, 0}
	}
	mx := z - x - 2
	mi := 2
	if y-x < 3 || z-y < 3 {
		mi = 1
	}
	return []int{mi, mx}
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}