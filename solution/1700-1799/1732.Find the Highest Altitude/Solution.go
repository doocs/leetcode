func largestAltitude(gain []int) int {
	res, t := 0, 0
	for _, h := range gain {
		t += h
		res = max(res, t)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}