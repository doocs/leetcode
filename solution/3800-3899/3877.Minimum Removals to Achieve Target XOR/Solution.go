func minRemovals(nums []int, target int) int {
	m := bits.Len(uint(slices.Max(nums)))
	if (1 << m) <= target {
		return -1
	}

	n := len(nums)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 1<<m)
		for j := range f[i] {
			f[i][j] = math.MinInt
		}
	}
	f[0][0] = 0

	for i := 1; i <= n; i++ {
		x := nums[i-1]
		for j := 0; j < (1 << m); j++ {
			f[i][j] = max(f[i-1][j], f[i-1][j^x]+1)
		}
	}

	if f[n][target] < 0 {
		return -1
	}
	return n - f[n][target]
}
