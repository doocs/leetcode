func minimumOneBitOperations(n int) int {
	if n == 0 {
		return 0
	}
	return n ^ minimumOneBitOperations(n>>1)
}