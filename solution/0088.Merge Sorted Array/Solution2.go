  func merge(nums1 []int, m int, nums2 []int, n int) {
	right, p1, p2 := m+n-1, m-1, n-1
	for p1 >= 0 && p2 >= 0 {
		if nums1[p1] < nums2[p2] {
			nums1[right] = nums2[p2]
			p2--
			right--
		} else if nums1[p1] >= nums2[p2] {
			nums1[right] = nums1[p1]
			p1--
			right--
		}
	}
    // copy(nums1, append(nums2[:p2+1], nums1[p2+1:]...))
	for i := 0; i <= p2; i++ {
		nums1[i] = nums2[i]
	}
}
