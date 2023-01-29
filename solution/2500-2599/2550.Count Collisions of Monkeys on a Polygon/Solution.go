func monkeyMove(n int) int {
	const mod = 1e9 + 7
	return (qmi(2, n, mod) - 2 + mod) % mod
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