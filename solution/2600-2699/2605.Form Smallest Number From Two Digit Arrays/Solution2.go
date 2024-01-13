func minNumber(nums1 []int, nums2 []int) int {
	s1 := [10]bool{}
	s2 := [10]bool{}
	for _, x := range nums1 {
		s1[x] = true
	}
	for _, x := range nums2 {
		s2[x] = true
	}
	a, b := 0, 0
	for i := 1; i < 10; i++ {
		if s1[i] && s2[i] {
			return i
		}
		if a == 0 && s1[i] {
			a = i
		}
		if b == 0 && s2[i] {
			b = i
		}
	}
	return min(a*10+b, b*10+a)
}