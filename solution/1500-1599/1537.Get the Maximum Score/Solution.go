func maxSum(nums1 []int, nums2 []int) int {
	const mod int = 1e9 + 7
	m, n := len(nums1), len(nums2)
	i, j := 0, 0
	f, g := 0, 0
	for i < m || j < n {
		if i == m {
			g += nums2[j]
			j++
		} else if j == n {
			f += nums1[i]
			i++
		} else if nums1[i] < nums2[j] {
			f += nums1[i]
			i++
		} else if nums1[i] > nums2[j] {
			g += nums2[j]
			j++
		} else {
			f = max(f, g) + nums1[i]
			g = f
			i++
			j++
		}
	}
	return max(f, g) % mod
}