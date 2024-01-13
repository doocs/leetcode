func findDerangement(n int) int {
	a, b := 1, 0
	const mod = 1e9 + 7
	for i := 2; i <= n; i++ {
		a, b = b, (i-1)*(a+b)%mod
	}
	return b
}