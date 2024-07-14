func minEatingSpeed(piles []int, h int) int {
	return 1 + sort.Search(slices.Max(piles), func(k int) bool {
		k++
		s := 0
		for _, x := range piles {
			s += (x + k - 1) / k
		}
		return s <= h
	})
}