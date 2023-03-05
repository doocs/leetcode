func waysToReachTarget(target int, types [][]int) int {
	n := len(types)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, target+1)
	}
	f[0][0] = 1
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		count, marks := types[i-1][0], types[i-1][1]
		for j := 0; j <= target; j++ {
			for k := 0; k <= count; k++ {
				if j >= k*marks {
					f[i][j] = (f[i][j] + f[i-1][j-k*marks]) % mod
				}
			}
		}
	}
	return f[n][target]
}