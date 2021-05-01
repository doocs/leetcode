func isUnique(astr string) bool {
	bitmap := 0
	for _, r := range astr {
		pos := r - 'a'
		if (bitmap & (1 << pos)) != 0 {
			return false
		}
		bitmap |= (1 << pos)
	}
	return true
}
