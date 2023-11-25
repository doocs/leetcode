func isConsecutive(nums []int) bool {
	s := map[int]bool{}
	mi, mx := slices.Min(nums), slices.Max(nums)
	for _, x := range nums {
		s[x] = true
	}
	return len(s) == len(nums) && mx == mi+len(nums)-1
}