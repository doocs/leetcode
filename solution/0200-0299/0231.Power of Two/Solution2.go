func isPowerOfTwo(n int) bool {
	return n > 0 && n == (n&(-n))
}