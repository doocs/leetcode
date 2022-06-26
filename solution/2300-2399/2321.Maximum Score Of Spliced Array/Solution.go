func maximumsSplicedArray(nums1 []int, nums2 []int) int {
	s1, s2 := 0, 0
	n := len(nums1)
	for i, v := range nums1 {
		s1 += v
		s2 += nums2[i]
	}
	f := func(nums1, nums2 []int) int {
		t := nums1[0] - nums2[0]
		mx := t
		for i := 1; i < n; i++ {
			v := nums1[i] - nums2[i]
			if t > 0 {
				t += v
			} else {
				t = v
			}
			mx = max(mx, t)
		}
		return mx
	}
	return max(s2+f(nums1, nums2), s1+f(nums2, nums1))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}