func countBits(n int) []int {
	f := make([]int, n+1)
	for i := 1; i <= n; i++ {
		f[i] = f[i&(i-1)] + 1
	}
	return f
}