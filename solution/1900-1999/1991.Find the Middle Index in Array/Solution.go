func findMiddleIndex(nums []int) int {
	s := 0
	for _, num := range nums {
		s += num
	}
	total := 0
	for i, num := range nums {
		total += num
		if total-num == s-total {
			return i
		}
	}
	return -1
}