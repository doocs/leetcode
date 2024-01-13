func waysToStep(n int) int {
	const mod int = 1e9 + 7
	a, b, c := 1, 2, 4
	for i := 1; i < n; i++ {
		a, b, c = b, c, (a+b+c)%mod
	}
	return a
}