func hasAlternatingBits(n int) bool {
	prev := -1
	for n != 0 {
		curr := n & 1
		if prev == curr {
			return false
		}
		prev = curr
		n >>= 1
	}
	return true
}