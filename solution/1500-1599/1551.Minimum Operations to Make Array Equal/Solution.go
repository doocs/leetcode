func minOperations(n int) (ans int) {
	for i := 0; i < n>>1; i++ {
		ans += n - (i<<1 | 1)
	}
	return
}