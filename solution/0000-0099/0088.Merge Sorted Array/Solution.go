func merge(nums1 []int, m int, nums2 []int, n int) {
	for i, j, k := m-1, n-1, m+n-1; j >= 0; k-- {
		if i >= 0 && nums1[i] > nums2[j] {
			nums1[k] = nums1[i]
			i--
		} else {
			nums1[k] = nums2[j]
			j--
		}
	}
}