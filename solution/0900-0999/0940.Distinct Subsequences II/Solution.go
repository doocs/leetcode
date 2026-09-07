func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	f := [26]int{}
	for _, c := range s {
		x := 1
		for _, v := range f {
			x = (x + v) % mod
		}
		f[c-'a'] = x
	}
	ans := 0
	for _, v := range f {
		ans = (ans + v) % mod
	}
	return ans
}
