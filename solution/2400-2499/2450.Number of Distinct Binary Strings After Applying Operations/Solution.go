func countDistinctStrings(s string, k int) int {
	const mod int = 1e9 + 7
	ans := 1
	for i := 0; i < len(s)-k+1; i++ {
		ans = (ans * 2) % mod
	}
	return ans
}