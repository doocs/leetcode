func numSubarrayProductLessThanK(nums []int, k int) int {
	n := len(nums)
	ans := 0
	sum := 1
	left, right := 0, 0
	for right < n {
		sum *= nums[right]
		right++
		for sum >= k && left < right {
			sum /= nums[left]
			left++
		}
		ans += right - left
	}
	return ans
}