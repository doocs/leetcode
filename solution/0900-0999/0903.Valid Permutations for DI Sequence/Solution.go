func numPermsDISequence(s string) (ans int) {
	const mod = 1e9 + 7
	n := len(s)
	f := make([]int, n+1)
	f[0] = 1
	for i := 1; i <= n; i++ {
		pre := 0
		g := make([]int, n+1)
		if s[i-1] == 'D' {
			for j := i; j >= 0; j-- {
				pre = (pre + f[j]) % mod
				g[j] = pre
			}
		} else {
			for j := 0; j <= i; j++ {
				g[j] = pre
				pre = (pre + f[j]) % mod
			}
		}
		f = g
	}
	for j := 0; j <= n; j++ {
		ans = (ans + f[j]) % mod
	}
	return
}