func hasAlternatingBits(n int) bool {
	n ^= (n >> 1)
	return (n & (n + 1)) == 0
}