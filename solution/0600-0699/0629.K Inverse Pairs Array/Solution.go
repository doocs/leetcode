func kInversePairs(n int, k int) int {
	f := make([]int, k+1)
	s := make([]int, k+2)
	f[0] = 1
	for i, x := range f {
		s[i+1] = s[i] + x
	}
	const mod = 1e9 + 7
	for i := 1; i <= n; i++ {
		for j := 1; j <= k; j++ {
			f[j] = (s[j+1] - s[max(0, j-(i-1))] + mod) % mod
		}
		for j := 1; j <= k+1; j++ {
			s[j] = (s[j-1] + f[j-1]) % mod
		}
	}
	return f[k]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

