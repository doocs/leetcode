func intersection(nums1 []int, nums2 []int) (ans []int) {
	s := [1001]bool{}
	for _, x := range nums1 {
		s[x] = true
	}
	for _, x := range nums2 {
		if s[x] {
			ans = append(ans, x)
			s[x] = false
		}
	}
	return
}