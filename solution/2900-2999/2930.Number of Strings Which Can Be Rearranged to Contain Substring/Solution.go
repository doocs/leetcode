func stringCount(n int) int {
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
	a := qpow(25, n)
	b := a
	c := qpow(25, n) + n*qpow(25, n-1)
	ab := qpow(24, n)
	ac := (qpow(24, n) + n*qpow(24, n-1)) % mod
	bc := ac
	abc := (qpow(23, n) + n*qpow(23, n-1)) % mod
	tot := qpow(26, n)
	return ((tot-(a+b+c-ab-ac-bc+abc))%mod + mod) % mod
}