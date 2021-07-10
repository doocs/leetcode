func buildArray(nums []int) []int {
	ans := make([]int, len(nums))
	for i, num := range nums {
		ans[i] = nums[num]
	}
	return ans
}