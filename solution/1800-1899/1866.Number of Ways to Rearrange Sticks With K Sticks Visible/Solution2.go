func rearrangeSticks(n int, k int) int {
	const mod = 1e9 + 7
	f := make([]int, k+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		for j := k; j > 0; j-- {
			f[j] = (f[j-1] + f[j]*(i-1)) % mod
		}
		f[0] = 0
	}
	return f[k]
}