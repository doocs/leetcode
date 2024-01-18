func subArrayRanges(nums []int) int64 {
	var ans int64
	n := len(nums)
	for i := 0; i < n-1; i++ {
		mi, mx := nums[i], nums[i]
		for j := i + 1; j < n; j++ {
			mi = min(mi, nums[j])
			mx = max(mx, nums[j])
			ans += (int64)(mx - mi)
		}
	}
	return ans
}