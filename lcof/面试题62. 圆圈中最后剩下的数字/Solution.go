func lastRemaining(n int, m int) int {
	f := 0
	for i := 2; i <= n; i++ {
		f = (f + m) % i
	}
	return f
}
