func findMedianSortedArrays(nums1 []int, nums2 []int) float64 {
	m, n := len(nums1), len(nums2)
	var f func(i, j, k int) int
	f = func(i, j, k int) int {
		if i >= m {
			return nums2[j+k-1]
		}
		if j >= n {
			return nums1[i+k-1]
		}
		if k == 1 {
			return min(nums1[i], nums2[j])
		}
		p := k / 2
		x, y := 1<<30, 1<<30
		if ni := i + p - 1; ni < m {
			x = nums1[ni]
		}
		if nj := j + p - 1; nj < n {
			y = nums2[nj]
		}
		if x < y {
			return f(i+p, j, k-p)
		}
		return f(i, j+p, k-p)
	}
	a, b := f(0, 0, (m+n+1)/2), f(0, 0, (m+n+2)/2)
	return float64(a+b) / 2.0
}