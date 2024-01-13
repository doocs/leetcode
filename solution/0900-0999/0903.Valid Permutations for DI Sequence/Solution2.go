func numPermsDISequence(s string) (ans int) {
	const mod = 1e9 + 7
	n := len(s)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, n+1)
	}
	f[0][0] = 1
	for i := 1; i <= n; i++ {
		pre := 0
		if s[i-1] == 'D' {
			for j := i; j >= 0; j-- {
				pre = (pre + f[i-1][j]) % mod
				f[i][j] = pre
			}
		} else {
			for j := 0; j <= i; j++ {
				f[i][j] = pre
				pre = (pre + f[i-1][j]) % mod
			}
		}
	}
	for j := 0; j <= n; j++ {
		ans = (ans + f[n][j]) % mod
	}
	return
}