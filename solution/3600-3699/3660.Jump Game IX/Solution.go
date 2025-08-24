func maxValue(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	preMax := make([]int, n)
	preMax[0] = nums[0]
	for i := 1; i < n; i++ {
		preMax[i] = max(preMax[i-1], nums[i])
	}
	sufMin := 1 << 30
	for i := n - 1; i >= 0; i-- {
		if preMax[i] > sufMin {
			ans[i] = ans[i+1]
		} else {
			ans[i] = preMax[i]
		}
		sufMin = min(sufMin, nums[i])
	}
	return ans
}
