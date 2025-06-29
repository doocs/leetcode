func minXor(nums []int, k int) int {
	n := len(nums)
	g := make([]int, n+1)
	for i := 1; i <= n; i++ {
		g[i] = g[i-1] ^ nums[i-1]
	}

	const inf = math.MaxInt32
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, k+1)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = 0

	for i := 1; i <= n; i++ {
		for j := 1; j <= min(i, k); j++ {
			for h := j - 1; h < i; h++ {
				f[i][j] = min(f[i][j], max(f[h][j-1], g[i]^g[h]))
			}
		}
	}

	return f[n][k]
}