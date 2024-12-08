func constructTransformedArray(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	for i, x := range nums {
		if x != 0 {
			ans[i] = nums[(i+x%n+n)%n]
		}
	}
	return ans
}
