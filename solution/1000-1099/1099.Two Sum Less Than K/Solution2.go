func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	for i, j := 0, len(nums)-1; i < j; {
		if s := nums[i] + nums[j]; s < k {
			ans = max(ans, s)
			i++
		} else {
			j--
		}
	}
	return ans
}