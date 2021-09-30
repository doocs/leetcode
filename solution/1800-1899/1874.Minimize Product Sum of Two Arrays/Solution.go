func minProductSum(nums1 []int, nums2 []int) int {
	sort.Ints(nums1)
	sort.Ints(nums2)
	res, n := 0, len(nums1)
	for i, num := range nums1 {
		res += num * nums2[n-i-1]
	}
	return res
}