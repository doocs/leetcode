func minBishopMoves(source []int, target []int) int {
	sr, sc := source[0], source[1]
	tr, tc := target[0], target[1]
	if (sr+sc)%2 != (tr+tc)%2 {
		return -1
	}
	if abs(sr-tr) == abs(sc-tc) {
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
