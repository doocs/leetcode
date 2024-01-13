func minOperations(nums1 []int, nums2 []int) int {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	d := s2 - s1
	arr := []int{}
	for _, v := range nums1 {
		arr = append(arr, 6-v)
	}
	for _, v := range nums2 {
		arr = append(arr, v-1)
	}
	sort.Sort(sort.Reverse(sort.IntSlice(arr)))
	for i, v := range arr {
		d -= v
		if d <= 0 {
			return i + 1
		}
	}
	return -1
}

func sum(nums []int) (s int) {
	for _, v := range nums {
		s += v
	}
	return
}