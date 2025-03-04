func findMiddleIndex(nums []int) int {
	l, r := 0, 0
	for _, x := range nums {
		r += x
	}
	for i, x := range nums {
		r -= x
		if l == r {
			return i
		}
		l += x
	}
	return -1
}
