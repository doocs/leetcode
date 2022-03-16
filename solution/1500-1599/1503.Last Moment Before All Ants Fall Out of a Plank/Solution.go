func getLastMoment(n int, left []int, right []int) int {
	ans := 0
	for _, t := range left {
		ans = max(ans, t)
	}
	for _, t := range right {
		ans = max(ans, n-t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}