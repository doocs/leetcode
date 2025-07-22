func triangularSum(nums []int) int {
	for k := len(nums) - 1; k > 0; k-- {
		for i := 0; i < k; i++ {
			nums[i] = (nums[i] + nums[i+1]) % 10
		}
	}
	return nums[0]
}
