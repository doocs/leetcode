func getMaxLen(nums []int) int {
	n := len(nums)
	var f, g int
	if nums[0] > 0 {
		f = 1
	} else if nums[0] < 0 {
		g = 1
	}
	ans := f
	for i := 1; i < n; i++ {
		ff, gg := 0, 0
		if nums[i] > 0 {
			ff = f + 1
			gg = 0
			if g > 0 {
				gg = g + 1
			}
		} else if nums[i] < 0 {
			ff = 0
			if g > 0 {
				ff = g + 1
			}
			gg = f + 1
		}
		f, g = ff, gg
		ans = max(ans, f)
	}
	return ans
}
