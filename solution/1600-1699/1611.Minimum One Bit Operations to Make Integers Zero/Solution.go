func minimumOneBitOperations(n int) (ans int) {
	for ; n > 0; n >>= 1 {
		ans ^= n
	}
	return
}