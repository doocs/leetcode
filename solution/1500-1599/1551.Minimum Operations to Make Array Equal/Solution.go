func minOperations(n int) int {
	ans := 0
	for i := 0; i < (n >> 1); i++ {
		ans += (n - (2*i + 1))
	}
	return ans
}