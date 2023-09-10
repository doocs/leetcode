func furthestDistanceFromOrigin(moves string) int {
	count := func(c string) int { return strings.Count(moves, c) }
	return abs(count("L")-count("R")) + count("_")
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}