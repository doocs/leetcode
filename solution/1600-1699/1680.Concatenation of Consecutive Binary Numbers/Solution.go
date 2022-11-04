func concatenatedBinary(n int) int {
	var ans, shift int
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		if i&(i-1) == 0 {
			shift++
		}
		ans = ((ans << shift) + i) % mod
	}
	return ans
}