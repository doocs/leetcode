func maxNonDecreasingLength(nums1 []int, nums2 []int) int {
	n := len(nums1)
	f, g, ans := 1, 1, 1
	for i := 1; i < n; i++ {
		ff, gg := 1, 1
		if nums1[i] >= nums1[i-1] {
			ff = max(ff, f+1)
		}
		if nums1[i] >= nums2[i-1] {
			ff = max(ff, g+1)
		}
		if nums2[i] >= nums1[i-1] {
			gg = max(gg, f+1)
		}
		if nums2[i] >= nums2[i-1] {
			gg = max(gg, g+1)
		}
		f, g = ff, gg
		ans = max(ans, max(f, g))
	}
	return ans
}