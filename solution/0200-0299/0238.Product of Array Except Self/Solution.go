func productExceptSelf(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	left, right := 1, 1
	for i, v := range nums {
		ans[i] = left
		left *= v
	}
	for i := n - 1; i >= 0; i-- {
		ans[i] *= right
		right *= nums[i]
	}
	return ans
}