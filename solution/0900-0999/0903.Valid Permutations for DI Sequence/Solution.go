func numPermsDISequence(s string) (ans int) {
	const mod = 1e9 + 7
	n := len(s)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		if s[i-1] == 'D' {
			for j := 0; j <= i; j++ {
				for k := j; k < i; k++ {
					f[i][j] = (f[i][j] + f[i-1][k]) % mod
				}
			}
		} else {
			for j := 0; j <= i; j++ {
				for k := 0; k < j; k++ {
					f[i][j] = (f[i][j] + f[i-1][k]) % mod
				}
			}
		}
	}
	for j := 0; j <= n; j++ {
		ans = (ans + f[n][j]) % mod
	}
	return
}