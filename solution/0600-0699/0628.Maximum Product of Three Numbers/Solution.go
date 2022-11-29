func maximumProduct(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	return max(nums[0]*nums[1]*nums[n-1], nums[n-1]*nums[n-2]*nums[n-3])
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
