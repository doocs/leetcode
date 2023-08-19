func findSwapValues(array1 []int, array2 []int) []int {
	s1, s2 := 0, 0
	s := map[int]bool{}
	for _, a := range array1 {
		s1 += a
	}
	for _, b := range array2 {
		s2 += b
		s[b] = true
	}
	diff := s1 - s2
	if (diff & 1) == 1 {
		return []int{}
	}
	diff >>= 1
	for _, a := range array1 {
		if b := a - diff; s[b] {
			return []int{a, b}
		}
	}
	return []int{}
}