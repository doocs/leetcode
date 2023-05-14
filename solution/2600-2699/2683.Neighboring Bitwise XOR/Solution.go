func doesValidArrayExist(derived []int) bool {
	s := 0
	for _, x := range derived {
		s ^= x
	}
	return s == 0
}