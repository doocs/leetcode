func countOrders(n int) int {
	const mod = 1e9 + 7
	f := 1
	for i := 2; i <= n; i++ {
		f = f * i * (2*i - 1) % mod
	}
	return f
}