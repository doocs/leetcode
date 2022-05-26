func hammingDistance(x int, y int) int {
	x ^= y
	count := 0
	for x != 0 {
		count++
		x &= (x - 1)
	}
	return count
}