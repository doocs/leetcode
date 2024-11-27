func knightDialer(n int) (ans int) {
	f := make([]int, 10)
	for i := range f {
		f[i] = 1
	}
	const mod int = 1e9 + 7
	for i := 1; i < n; i++ {
		g := make([]int, 10)
		g[0] = (f[4] + f[6]) % mod
		g[1] = (f[6] + f[8]) % mod
		g[2] = (f[7] + f[9]) % mod
		g[3] = (f[4] + f[8]) % mod
		g[4] = (f[0] + f[3] + f[9]) % mod
		g[6] = (f[0] + f[1] + f[7]) % mod
		g[7] = (f[2] + f[6]) % mod
		g[8] = (f[1] + f[3]) % mod
		g[9] = (f[2] + f[4]) % mod
		f = g
	}
	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}
