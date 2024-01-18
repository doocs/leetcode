func numWays(n int, relation [][]int, k int) int {
	f := make([][]int, k+1)
	for i := range f {
		f[i] = make([]int, n)
	}
	f[0][0] = 1
	for i := 1; i <= k; i++ {
		for _, r := range relation {
			a, b := r[0], r[1]
			f[i][b] += f[i-1][a]
		}
	}
	return f[k][n-1]
}