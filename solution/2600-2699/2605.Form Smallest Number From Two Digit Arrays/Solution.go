func minNumber(nums1 []int, nums2 []int) int {
	ans := 100
	for _, a := range nums1 {
		for _, b := range nums2 {
			if a == b {
				ans = min(ans, a)
			} else {
				ans = min(ans, min(a*10+b, b*10+a))
			}
		}
	}
	return ans
}