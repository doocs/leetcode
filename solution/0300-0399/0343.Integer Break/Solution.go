func integerBreak(n int) int {
	f := make([]int, n+1)
	f[1] = 1
	for i := 2; i <= n; i++ {
		for j := 1; j < i; j++ {
			f[i] = max(max(f[i], f[i-j]*j), (i-j)*j)
		}
	}
	return f[n]
}