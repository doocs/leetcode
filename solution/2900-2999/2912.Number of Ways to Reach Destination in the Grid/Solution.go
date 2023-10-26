func numberOfWays(n int, m int, k int, source []int, dest []int) int {
	const mod int = 1e9 + 7
	f := []int{1, 0, 0, 0}
	for i := 0; i < k; i++ {
		g := make([]int, 4)
		g[0] = ((n-1)*f[1] + (m-1)*f[2]) % mod
		g[1] = (f[0] + (n-2)*f[1] + (m-1)*f[3]) % mod
		g[2] = (f[0] + (m-2)*f[2] + (n-1)*f[3]) % mod
		g[3] = (f[1] + f[2] + (n-2)*f[3] + (m-2)*f[3]) % mod
		f = g
	}

	if source[0] == dest[0] {
		if source[1] == dest[1] {
			return f[0]
		}
		return f[2]
	}

	if source[1] == dest[1] {
		return f[1]
	}
	return f[3]
}