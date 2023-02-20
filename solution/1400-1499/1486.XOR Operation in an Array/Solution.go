func xorOperation(n int, start int) (ans int) {
	for i := 0; i < n; i++ {
		ans ^= start + 2*i
	}
	return
}