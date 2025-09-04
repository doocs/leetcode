func sortPermutation(nums []int) int {
	ans := -1
	for i, x := range nums {
		if i != x {
			ans &= x
		}
	}
	return max(ans, 0)
}
