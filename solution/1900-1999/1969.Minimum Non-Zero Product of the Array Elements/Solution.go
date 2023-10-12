func minNonZeroProduct(p int) int {
	const mod int = 1e9 + 7
	qpow := func(a, n int) int {
		ans := 1
		for ; n > 0; n >>= 1 {
			if n&1 == 1 {
				ans = ans * a % mod
			}
			a = a * a % mod
		}
		return ans
	}
	a := ((1 << p) - 1) % mod
	b := qpow(((1<<p)-2)%mod, (1<<(p-1))-1)
	return a * b % mod
}