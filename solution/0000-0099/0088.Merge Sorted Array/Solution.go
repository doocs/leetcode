func merge(nums1 []int, m int, nums2 []int, n int) {
	ret := make([]int, m+n)
	copy(ret, nums1[:m])
	p0, p1, p2 := 0, 0, 0
	for ; p1 < m && p2 < n; p0++ {
		if nums1[p1] >= nums2[p2] {
			ret[p0] = nums2[p2]
			p2++
		} else if nums1[p1] < nums2[p2] {
			ret[p0] = nums1[p1]
			p1++
		}
	}
	if p1 < m {
		copy(nums1, append(ret[:p0], nums1[p1:m]...))
	}
	if p2 < n {
		copy(nums1, append(ret[:p0], nums2[p2:]...))
	}
}
