func minSum(nums1 []int, nums2 []int) int64 {
	s1, s2 := 0, 0
	hasZero := false
	for _, x := range nums1 {
		if x == 0 {
			hasZero = true
		}
		s1 += max(x, 1)
	}
	for _, x := range nums2 {
		s2 += max(x, 1)
	}
	if s1 > s2 {
		return minSum(nums2, nums1)
	}
	if s1 == s2 {
		return int64(s1)
	}
	if hasZero {
		return int64(s2)
	}
	return -1
}