func minimizeSum(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	return min(nums[n-1]-nums[2], min(nums[n-2]-nums[1], nums[n-3]-nums[0]))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}