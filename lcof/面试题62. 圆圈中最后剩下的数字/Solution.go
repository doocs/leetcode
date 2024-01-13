func lastRemaining(n int, m int) int {
	var f func(n, m int) int
	f = func(n, m int) int {
		if n == 1 {
			return 0
		}
		x := f(n-1, m)
		return (m + x) % n
	}
	return f(n, m)
}