func triangularSum(nums []int) int {
	n := len(nums)
	for i := n; i >= 0; i-- {
		for j := 0; j < i-1; j++ {
			nums[j] = (nums[j] + nums[j+1]) % 10
		}
	}
	return nums[0]
}