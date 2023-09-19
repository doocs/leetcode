func cuttingRope(n int) int {
	if n < 4 {
		return n - 1
	}
	const mod = 1e9 + 7
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
	if n%3 == 0 {
		return qpow(3, n/3)
	}
	if n%3 == 1 {
		return qpow(3, n/3-1) * 4 % mod
	}
	return qpow(3, n/3) * 2 % mod
}