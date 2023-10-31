func maxSubArray(nums []int) int {
	ans, f := -1000000000, 0
	for _, x := range nums {
		f = max(f, 0) + x
		ans = max(ans, f)
	}
	return ans
}