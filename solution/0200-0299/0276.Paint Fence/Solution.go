func numWays(n int, k int) int {
	f := make([]int, n)
	g := make([]int, n)
	f[0] = k
	for i := 1; i < n; i++ {
		f[i] = (f[i-1] + g[i-1]) * (k - 1)
		g[i] = f[i-1]
	}
	return f[n-1] + g[n-1]
}