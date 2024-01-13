func distinctSubseqII(s string) int {
	const mod int = 1e9 + 7
	sum := func(arr []int) int {
		x := 0
		for _, v := range arr {
			x = (x + v) % mod
		}
		return x
	}

	dp := make([]int, 26)
	for _, c := range s {
		c -= 'a'
		dp[c] = sum(dp) + 1
	}
	return sum(dp)
}