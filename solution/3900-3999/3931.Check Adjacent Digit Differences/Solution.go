func isAdjacentDiffAtMostTwo(s string) bool {
	for i := 1; i < len(s); i++ {
		if abs(int(s[i-1])-int(s[i])) > 2 {
			return false
		}
	}
	return true
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}