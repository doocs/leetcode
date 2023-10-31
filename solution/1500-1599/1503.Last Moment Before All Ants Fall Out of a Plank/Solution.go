func getLastMoment(n int, left []int, right []int) (ans int) {
	for _, x := range left {
		ans = max(ans, x)
	}
	for _, x := range right {
		ans = max(ans, n-x)
	}
	return
}