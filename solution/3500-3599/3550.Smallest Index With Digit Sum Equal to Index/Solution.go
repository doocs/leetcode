func smallestIndex(nums []int) int {
	for i, x := range nums {
		s := 0
		for ; x > 0; x /= 10 {
			s += x % 10
		}
		if s == i {
			return i
		}
	}
	return -1
}