func maximumSetSize(nums1 []int, nums2 []int) int {
	s1 := map[int]bool{}
	s2 := map[int]bool{}
	for _, x := range nums1 {
		s1[x] = true
	}
	for _, x := range nums2 {
		s2[x] = true
	}
	a, b, c := 0, 0, 0
	for x := range s1 {
		if !s2[x] {
			a++
		}
	}
	for x := range s2 {
		if !s1[x] {
			b++
		} else {
			c++
		}
	}
	n := len(nums1)
	a = min(a, n/2)
	b = min(b, n/2)
	return min(a+b+c, n)
}