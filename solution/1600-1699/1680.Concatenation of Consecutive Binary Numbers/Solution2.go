func concatenatedBinary(n int) (ans int) {
	const mod = 1e9 + 7
	shift := 0
	for i := 1; i <= n; i++ {
		if i&(i-1) == 0 {
			shift++
		}
		ans = (ans<<shift | i) % mod
	}
	return
}