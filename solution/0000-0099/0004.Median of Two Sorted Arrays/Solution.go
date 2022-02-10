func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	m, n := len(nums1), len(nums2)
	left, right := (m+n+1)/2, (m+n+2)/2
	var findKth func(i, j, k int) int
	findKth = func(i, j, k int) int {
		if i >= m {
			return nums2[j+k-1]
		}
		if j >= n {
			return nums1[i+k-1]
		}
		if k == 1 {
			return min(nums1[i], nums2[j])
		}
		midVal1 := math.MaxInt32
		midVal2 := math.MaxInt32
		if i+k/2-1 < m {
			midVal1 = nums1[i+k/2-1]
		}
		if j+k/2-1 < n {
			midVal2 = nums2[j+k/2-1]
		}
		if midVal1 < midVal2 {
			return findKth(i+k/2, j, k-k/2)
		}
		return findKth(i, j+k/2, k-k/2)
	}
	return (float64(findKth(0, 0, left)) + float64(findKth(0, 0, right))) / 2.0
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}