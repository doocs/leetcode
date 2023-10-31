func maxProduct(nums []int) int {
	f, g, ans := nums[0], nums[0], nums[0]
	for _, x := range nums[1:] {
		ff, gg := f, g
		f = max(x, max(ff*x, gg*x))
		g = min(x, min(ff*x, gg*x))
		ans = max(ans, f)
	}
	return ans
}