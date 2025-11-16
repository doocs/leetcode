func numSub(s string) (ans int) {
	const mod int = 1e9 + 7
	cur := 0
	for _, c := range s {
		if c == '0' {
			cur = 0
		} else {
			cur++
			ans = (ans + cur) % mod
		}
	}
	return
}
