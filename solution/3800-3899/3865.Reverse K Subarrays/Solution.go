func reverseSubarrays(nums []int, k int) []int {
	n := len(nums)
	m := n / k
	for i := 0; i < n; i += m {
		l, r := i, i+m-1
		for l < r {
			nums[l], nums[r] = nums[r], nums[l]
			l++
			r--
		}
	}
	return nums
}
