func missingNumber(nums []int) int {
	n := len(nums)
	res := n
	for i := 0; i < n; i++ {
		res ^= (i ^ nums[i])
	}
	return res
}
