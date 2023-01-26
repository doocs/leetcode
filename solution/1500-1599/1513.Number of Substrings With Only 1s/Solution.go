func numSub(s string) (ans int) {
	const mod = 1e9 + 7
	cnt := 0
	for _, c := range s {
		if c == '1' {
			cnt++
		} else {
			cnt = 0
		}
		ans = (ans + cnt) % mod
	}
	return
}