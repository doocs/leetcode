func numMusicPlaylists(n int, goal int, k int) int {
	const mod = 1e9 + 7
	f := make([]int, goal+1)
	f[0] = 1
	for i := 1; i <= goal; i++ {
		g := make([]int, goal+1)
		for j := 1; j <= n; j++ {
			g[j] = f[j-1] * (n - j + 1)
			if j > k {
				g[j] += f[j] * (j - k)
			}
			g[j] %= mod
		}
		f = g
	}
	return f[n]
}