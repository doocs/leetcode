func numSubarrayProductLessThanK(nums []int, k int) int {
	s := 1
	ans, i := 0, 0
	for j, x := range nums {
		s *= x
		for i <= j && s >= k {
			s /= nums[i]
			i++
		}
		ans += j - i + 1
	}
	return ans
}