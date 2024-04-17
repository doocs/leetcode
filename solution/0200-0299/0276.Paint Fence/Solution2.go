func numWays(n int, k int) int {
	f, g := k, 0
	for i := 1; i < n; i++ {
		f, g = (f+g)*(k-1), f
	}
	return f + g
}