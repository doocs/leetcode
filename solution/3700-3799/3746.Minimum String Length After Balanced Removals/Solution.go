func minLengthAfterRemovals(s string) int {
	a := strings.Count(s, "a")
	b := len(s) - a
	return abs(a - b)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
