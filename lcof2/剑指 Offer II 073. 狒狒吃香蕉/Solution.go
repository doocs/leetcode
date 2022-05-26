func minEatingSpeed(piles []int, h int) int {
	mx := 0
	for _, pile := range piles {
		mx = max(mx, pile)
	}
	left, right := 1, mx
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

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}