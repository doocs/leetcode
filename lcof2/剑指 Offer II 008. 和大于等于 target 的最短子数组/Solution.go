func minSubArrayLen(target int, nums []int) int {
	const inf = 1 << 30
	ans := inf
	s, i := 0, 0
	for j, x := range nums {
		s += x
		for s >= target {
			ans = min(ans, j-i+1)
			s -= nums[i]
			i++
		}
	}
	if ans == inf {
		return 0
	}
	return ans
}