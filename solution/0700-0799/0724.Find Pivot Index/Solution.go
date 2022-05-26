func pivotIndex(nums []int) int {
	s := 0
	for _, e := range nums {
		s += e
	}
	presum := 0
	for i, e := range nums {
		if presum<<1 == s-e {
			return i
		}
		presum += e
	}
	return -1
}