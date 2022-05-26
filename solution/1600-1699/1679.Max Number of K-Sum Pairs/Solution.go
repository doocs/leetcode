func maxOperations(nums []int, k int) int {
	sort.Ints(nums)
	l, r, ans := 0, len(nums)-1, 0
	for l < r {
		s := nums[l] + nums[r]
		if s == k {
			ans++
			l++
			r--
		} else if s > k {
			r--
		} else {
			l++
		}
	}
	return ans
}