func cuttingRope(n int) int {
	if n < 4 {
		return n - 1
	}
	const mod = 1e9 + 7
	if n%3 == 0 {
		return qmi(3, n/3, mod)
	}
	if n%3 == 1 {
		return qmi(3, n/3-1, mod) * 4 % mod
	}
	return qmi(3, n/3, mod) * 2 % mod
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