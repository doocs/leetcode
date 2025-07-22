func findClosest(x int, y int, z int) int {
	a, b := abs(x-z), abs(y-z)
	if a == b {
		return 0
	}
	if a < b {
		return 1
	}
	return 2
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}