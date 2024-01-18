func minimumCosts(regular []int, express []int, expressCost int) []int64 {
	n := len(regular)
	f := make([]int, n+1)
	g := make([]int, n+1)
	g[0] = 1 << 30
	cost := make([]int64, n)
	for i := 1; i <= n; i++ {
		a, b := regular[i-1], express[i-1]
		f[i] = min(f[i-1]+a, g[i-1]+a)
		g[i] = min(f[i-1]+expressCost+b, g[i-1]+b)
		cost[i-1] = int64(min(f[i], g[i]))
	}
	return cost
}