func numberOfUniqueGoodSubsequences(binary string) (ans int) {
	const mod int = 1e9 + 7
	f, g := 0, 0
	for _, c := range binary {
		if c == '0' {
			g = (g + f) % mod
			ans = 1
		} else {
			f = (f + g + 1) % mod
		}
	}
	ans = (ans + f + g) % mod
	return
}