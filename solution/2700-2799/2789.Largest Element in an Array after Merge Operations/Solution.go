func maxArrayValue(nums []int) int64 {
	n := len(nums)
	ans, t := nums[n-1], nums[n-1]
	for i := n - 2; i >= 0; i-- {
		if nums[i] <= t {
			t += nums[i]
		} else {
			t = nums[i]
		}
		ans = max(ans, t)
	}
	return int64(ans)
}