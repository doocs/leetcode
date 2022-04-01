func findDifference(nums1 []int, nums2 []int) [][]int {
	s1, s2 := make(map[int]bool), make(map[int]bool)
	for _, v := range nums1 {
		s1[v] = true
	}
	for _, v := range nums2 {
		s2[v] = true
	}
	ans := make([][]int, 2)
	for v := range s1 {
		if !s2[v] {
			ans[0] = append(ans[0], v)
		}
	}
	for v := range s2 {
		if !s1[v] {
			ans[1] = append(ans[1], v)
		}
	}
	return ans
}