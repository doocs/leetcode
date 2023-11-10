func minEatingSpeed(piles []int, h int) int {
	left, right := 1, slices.Max(piles)
	for left < right {
		mid := (left + right) >> 1
		s := 0
		for _, pile := range piles {
			s += (pile + mid - 1) / mid
		}
		if s <= h {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}