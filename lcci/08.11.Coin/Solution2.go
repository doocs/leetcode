func waysToChange(n int) int {
	const mod int = 1e9 + 7
	coins := []int{25, 10, 5, 1}
	f := make([]int, n+1)
	f[0] = 1
	for _, c := range coins {
		for j := c; j <= n; j++ {
			f[j] = (f[j] + f[j-c]) % mod
		}
	}
	return f[n]
}