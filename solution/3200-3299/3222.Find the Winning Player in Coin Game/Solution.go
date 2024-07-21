func losingPlayer(x int, y int) string {
	k := min(x/2, y/8)
	x -= 2 * k
	y -= 8 * k
	if x > 0 && y >= 4 {
		return "Alice"
	}
	return "Bob"
}