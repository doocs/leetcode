func peakValleyDifference(nums []int) int {
	ans := 0
	for i := 1; i < len(nums)-1; i++ {
		if nums[i-1] < nums[i] && nums[i] > nums[i+1] {
			ans += nums[i]
		} else if nums[i-1] > nums[i] && nums[i] < nums[i+1] {
			ans -= nums[i]
		}
	}
	return ans
}
