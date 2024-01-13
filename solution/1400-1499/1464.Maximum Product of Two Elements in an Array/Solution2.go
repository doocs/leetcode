func maxProduct(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	return (nums[n-1] - 1) * (nums[n-2] - 1)
}