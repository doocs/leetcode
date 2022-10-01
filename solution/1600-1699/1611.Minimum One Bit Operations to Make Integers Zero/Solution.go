func minimumOneBitOperations(n int) int {
	if n <= 1 {
		return n
	}
	base := 0
	for i := 0; i < 64; i++ {
		if (n >> i) == 1 {
			base = 1 << i
			break
		}
	}
	return (base << 1) - 1 - minimumOneBitOperations(n-base)
}
