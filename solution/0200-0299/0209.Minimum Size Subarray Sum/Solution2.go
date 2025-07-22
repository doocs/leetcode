func minSubArrayLen(target int, nums []int) int {
	l, n := 0, len(nums)
	s, ans := 0, n+1
	for r, x := range nums {
		s += x
		for s >= target {
			ans = min(ans, r-l+1)
			s -= nums[l]
			l++
		}
	}
	if ans > n {
		return 0
	}
	return ans
}
