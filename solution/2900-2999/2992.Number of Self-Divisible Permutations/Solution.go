func selfDivisiblePermutationCount(n int) int {
	f := make([]int, 1<<(n+1))
	for i := range f {
		f[i] = -1
	}
	var dfs func(int) int
	dfs = func(mask int) int {
		if f[mask] != -1 {
			return f[mask]
		}
		i := bits.OnesCount(uint(mask)) + 1
		if i > n {
			return 1
		}
		f[mask] = 0
		for j := 1; j <= n; j++ {
			if mask>>j&1 == 0 && (i%j == 0 || j%i == 0) {
				f[mask] += dfs(mask | 1<<j)
			}
		}
		return f[mask]
	}
	return dfs(0)
}