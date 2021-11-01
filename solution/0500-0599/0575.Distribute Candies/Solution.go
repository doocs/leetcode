func distributeCandies(candyType []int) int {
	s := hashset.New()
	for _, c := range candyType {
		s.Add(c)
	}
	return min(len(candyType)>>1, s.Size())
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}