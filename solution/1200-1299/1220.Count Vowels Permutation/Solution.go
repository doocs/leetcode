func countVowelPermutation(n int) (ans int) {
	const mod int = 1e9 + 7
	f := make([]int, 5)
	for i := range f {
		f[i] = 1
	}
	for i := 1; i < n; i++ {
		g := make([]int, 5)
		g[0] = (f[1] + f[2] + f[4]) % mod
		g[1] = (f[0] + f[2]) % mod
		g[2] = (f[1] + f[3]) % mod
		g[3] = f[2] % mod
		g[4] = (f[2] + f[3]) % mod
		f = g
	}
	for _, x := range f {
		ans = (ans + x) % mod
	}
	return
}