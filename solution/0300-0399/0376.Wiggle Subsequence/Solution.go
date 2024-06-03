func wiggleMaxLength(nums []int) int {
	n := len(nums)
	f := make([]int, n)
	g := make([]int, n)
	f[0], g[0] = 1, 1
	ans := 1
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			if nums[j] < nums[i] {
				f[i] = max(f[i], g[j]+1)
			} else if nums[j] > nums[i] {
				g[i] = max(g[i], f[j]+1)
			}
		}
		ans = max(ans, max(f[i], g[i]))
	}
	return ans
}