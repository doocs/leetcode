func superPow(a int, b []int) int {
	const mod int = 1337
	ans := 1
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
	for i := len(b) - 1; i >= 0; i-- {
		ans = ans * qpow(a, b[i]) % mod
		a = qpow(a, 10)
	}
	return ans
}