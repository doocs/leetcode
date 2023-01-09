func maxNiceDivisors(primeFactors int) int {
	if primeFactors < 4 {
		return primeFactors
	}
	const mod int = 1e9 + 7
	if primeFactors%3 == 0 {
		return qmi(3, primeFactors/3, mod)
	}
	if primeFactors%3 == 1 {
		return 4 * qmi(3, primeFactors/3-1, mod) % mod
	}
	return 2 * qmi(3, primeFactors/3, mod) % mod
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