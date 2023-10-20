func numRollsToTarget(n int, k int, target int) int {
	const mod int = 1e9 + 7
	f := make([]int, target+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		g := make([]int, target+1)
		for j := 1; j <= min(target, i*k); j++ {
			for h := 1; h <= min(j, k); h++ {
				g[j] = (g[j] + f[j-h]) % mod
			}
		}
		f = g
	}
	return f[target]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}