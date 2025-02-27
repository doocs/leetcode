func fairCandySwap(aliceSizes []int, bobSizes []int) []int {
	s1, s2 := 0, 0
	s := map[int]bool{}
	for _, a := range aliceSizes {
		s1 += a
	}
	for _, b := range bobSizes {
		s2 += b
		s[b] = true
	}
	diff := (s1 - s2) / 2
	for _, a := range aliceSizes {
		if b := a - diff; s[b] {
			return []int{a, b}
		}
	}
	return nil
}
