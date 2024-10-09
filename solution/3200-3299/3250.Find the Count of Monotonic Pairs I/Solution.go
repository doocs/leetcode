func countOfPairs(nums []int) (ans int) {
	const mod int = 1e9 + 7
	n := len(nums)
	m := slices.Max(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, m+1)
	}
	for j := 0; j <= nums[0]; j++ {
		f[0][j] = 1
	}
	g := make([]int, m+1)
	for i := 1; i < n; i++ {
		g[0] = f[i-1][0]
		for j := 1; j <= m; j++ {
			g[j] = (g[j-1] + f[i-1][j]) % mod
		}
		for j := 0; j <= nums[i]; j++ {
			k := min(j, j+nums[i-1]-nums[i])
			if k >= 0 {
				f[i][j] = g[k]
			}
		}
	}
	for j := 0; j <= nums[n-1]; j++ {
		ans = (ans + f[n-1][j]) % mod
	}
	return
}
