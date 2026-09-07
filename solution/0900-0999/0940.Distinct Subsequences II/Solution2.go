func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	f := [26]int{}
	ans := 0
	for _, c := range s {
		i := c - 'a'
		add := (ans + 1 + mod - f[i]) % mod
		ans = (ans + add) % mod
		f[i] = (f[i] + add) % mod
	}
	return ans
}
