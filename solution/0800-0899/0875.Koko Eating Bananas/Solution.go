func minEatingSpeed(piles []int, h int) int {
	left, right := 1, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, x := range piles {
			s += (x + mid - 1) / mid
		}
		if s <= h {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}