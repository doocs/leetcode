func minNonZeroProduct(p int) int {
	const mod int = 1e9 + 7
	a := ((1 << p) - 1) % mod
	b := qmi(((1<<p)-2)%mod, (1<<(p-1))-1, mod)
	return a * b % mod
}

func qmi(a, k, p int) int {
	res := 1
	for k != 0 {
		if k&1 == 1 {
			res = res * a % p
		}
		k >>= 1
		a = a * a % p
	}
	return res
}