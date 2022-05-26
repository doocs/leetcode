func intersect(nums1 []int, nums2 []int) []int {
	counter := make(map[int]int)
	for _, num := range nums1 {
		counter[num]++
	}
	var res []int
	for _, num := range nums2 {
		if counter[num] > 0 {
			counter[num]--
			res = append(res, num)
		}
	}
	return res
}