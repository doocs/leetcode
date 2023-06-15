func minEatingSpeed(piles []int, h int) int {
	return sort.Search(1e9, func(i int) bool {
		if i == 0 {
			return false
		}
		s := 0
		for _, x := range piles {
			s += (x + i - 1) / i
		}
		return s <= h
	})
}