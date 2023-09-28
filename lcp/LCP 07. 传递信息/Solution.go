func numWays(n int, relation [][]int, k int) int {
	f := make([]int, n)
	f[0] = 1
	for ; k > 0; k-- {
		g := make([]int, n)
		for _, r := range relation {
			a, b := r[0], r[1]
			g[b] += f[a]
		}
		f = g
	}
	return f[n-1]
}