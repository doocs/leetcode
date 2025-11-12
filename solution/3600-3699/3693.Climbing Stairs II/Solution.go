func climbStairs(n int, costs []int) int {
	const inf = int(1e9)
	f := make([]int, n+1)
	for i := range f {
		f[i] = inf
	}
	f[0] = 0
	for i := 1; i <= n; i++ {
		x := costs[i-1]
		for j := max(0, i-3); j < i; j++ {
			f[i] = min(f[i], f[j]+x+(i-j)*(i-j))
		}
	}
	return f[n]
}
