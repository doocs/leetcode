func numRollsToTarget(n int, k int, target int) int {
	const mod int = 1e9 + 7
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, target+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		for j := 1; j <= min(target, i*k); j++ {
			for h := 1; h <= min(j, k); h++ {
				f[i][j] = (f[i][j] + f[i-1][j-h]) % mod
			}
		}
	}
	return f[n][target]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}