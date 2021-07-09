func maxSubarraySumCircular(nums []int) int {
	s1, s2, f1, f2, total := nums[0], nums[0], nums[0], nums[0], nums[0]
	for i := 1; i < len(nums); i++ {
		total += nums[i]
		f1 = nums[i] + max(f1, 0)
		f2 = nums[i] + min(f2, 0)
		s1 = max(s1, f1)
		s2 = min(s2, f2)
	}
	if s1 <= 0 {
		return s1
	}
	return max(s1, total-s2)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}