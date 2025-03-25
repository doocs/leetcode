func maxScore(nums []int) int {
	const inf = 1 << 30
	n := len(nums)
	s, mi, t := 0, inf, inf
	for i, x := range nums {
		s += x
		mi = min(mi, x)
		if i+1 < n {
			t = min(t, x+nums[i+1])
		}
	}
	if n%2 == 1 {
		return s - mi
	}
	return s - t
}