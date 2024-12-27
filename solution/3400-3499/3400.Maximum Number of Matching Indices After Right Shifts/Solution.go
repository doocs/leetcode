func maximumMatchingIndices(nums1 []int, nums2 []int) (ans int) {
	n := len(nums1)
	for k := range nums1 {
		t := 0
		for i, x := range nums2 {
			if nums1[(i+k)%n] == x {
				t++
			}
		}
		ans = max(ans, t)
	}
	return
}
