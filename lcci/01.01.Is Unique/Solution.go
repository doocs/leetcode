func isUnique(astr string) bool {
	mask := 0
	for _, c := range astr {
		i := c - 'a'
		if mask>>i&1 == 1 {
			return false
		}
		mask |= 1 << i
	}
	return true
}