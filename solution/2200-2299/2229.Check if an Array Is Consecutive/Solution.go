func isConsecutive(nums []int) bool {
	s := make(map[int]bool)
	mi, mx := nums[0], nums[0]
	for _, v := range nums {
		s[v] = true
		mi = min(mi, v)
		mx = max(mx, v)
	}
	return len(s) == len(nums) && mx == mi+len(nums)-1
}