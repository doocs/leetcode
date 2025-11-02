func maxProduct(nums []int) int64 {
	sort.Ints(nums)
	n := len(nums)
	a, b := int64(nums[0]), int64(nums[1])
	c, d := int64(nums[n-2]), int64(nums[n-1])
	const x int64 = 100000
	return max(a*b*x, c*d*x, -a*d*x)
}
