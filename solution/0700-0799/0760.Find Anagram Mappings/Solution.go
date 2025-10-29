func anagramMappings(nums1 []int, nums2 []int) []int {
	d := map[int]int{}
	for i, x := range nums2 {
		d[x] = i
	}
	ans := make([]int, len(nums1))
	for i, x := range nums1 {
		ans[i] = d[x]
	}
	return ans
}
