func numberOfPairs(nums1 []int, nums2 []int, k int) (ans int) {
	for _, x := range nums1 {
		for _, y := range nums2 {
			if x%(y*k) == 0 {
				ans++
			}
		}
	}
	return
}