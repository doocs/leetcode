func getMaxLen(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	g := make([]int, n)
	if nums[0] > 0 {
		f[0] = 1
	}
	if nums[0] < 0 {
		g[0] = 1
	}
	ans := f[0]

	for i := 1; i < n; i++ {
		if nums[i] > 0 {
			f[i] = f[i-1] + 1
			if g[i-1] > 0 {
				g[i] = g[i-1] + 1
			} else {
				g[i] = 0
			}
		} else if nums[i] < 0 {
			if g[i-1] > 0 {
				f[i] = g[i-1] + 1
			} else {
				f[i] = 0
			}
			g[i] = f[i-1] + 1
		}
		ans = max(ans, f[i])
	}
	return ans
}
