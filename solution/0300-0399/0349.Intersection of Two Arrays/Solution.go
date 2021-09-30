func intersection(nums1 []int, nums2 []int) []int {
	s := make(map[int]bool)
	for _, num := range nums1 {
		s[num] = true
	}
	t := make(map[int]bool)
	var res []int
	for _, num := range nums2 {
		if s[num] && !t[num] {
			res = append(res, num)
			t[num] = true
		}
	}
	return res
}