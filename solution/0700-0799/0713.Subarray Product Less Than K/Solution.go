func numSubarrayProductLessThanK(nums []int, k int) int {
	ans := 0
	for i, j, s := 0, 0, 1; i < len(nums); i++ {
		s *= nums[i]
		for ; j <= i && s >= k; j++ {
			s /= nums[j]
		}
		ans += i - j + 1
	}
	return ans
}