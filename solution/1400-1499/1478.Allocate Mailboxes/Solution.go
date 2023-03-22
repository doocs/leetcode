func minDistance(houses []int, k int) int {
	sort.Ints(houses)
	n := len(houses)
	g := make([][]int, n)
	f := make([][]int, n)
	const inf = 1 << 30
	for i := range g {
		g[i] = make([]int, n)
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			g[i][j] = g[i+1][j-1] + houses[j] - houses[i]
		}
	}
	for i := 0; i < n; i++ {
		f[i][1] = g[0][i]
		for j := 2; j <= k && j <= i+1; j++ {
			for p := 0; p < i; p++ {
				f[i][j] = min(f[i][j], f[p][j-1]+g[p+1][i])
			}
		}
	}
	return f[n-1][k]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}