func minimumPossibleSum(n int, target int) int {
	const mod int = 1e9 + 7
	m := target / 2
	if n <= m {
		return (n + 1) * n / 2 % mod
	}
	a := (m + 1) * m / 2 % mod
	b := (target + target + n - m - 1) * (n - m) / 2 % mod
	return (a + b) % mod
}