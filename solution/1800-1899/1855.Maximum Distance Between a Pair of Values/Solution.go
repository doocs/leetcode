func maxDistance(nums1 []int, nums2 []int) int {
	res, n := 0, len(nums2)
	for i, num := range nums1 {
		left, right := i, n-1
		for left < right {
			mid := (left + right + 1) >> 1
			if nums2[mid] >= num {
				left = mid
			} else {
				right = mid - 1
			}
		}
		if res < left-i {
			res = left - i
		}
	}
	return res
}