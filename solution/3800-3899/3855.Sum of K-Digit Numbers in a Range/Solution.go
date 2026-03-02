func sumOfNumbers(l int, r int, k int) int {
	const mod int64 = 1_000_000_007

	n := int64(r - l + 1)

	// ((l + r) * (r - l + 1) / 2) % mod
	sum := int64(l+r) * n / 2 % mod

	// pow(r - l + 1, k - 1, mod)
	part1 := qpow(n%mod, int64(k-1), mod)

	// (pow(10, k, mod) - 1)
	part2 := (qpow(10, int64(k), mod) - 1 + mod) % mod

	// Fermat inverse of 9
	inv9 := qpow(9, mod-2, mod)

	ans := sum
	ans = ans * part1 % mod
	ans = ans * part2 % mod
	ans = ans * inv9 % mod

	return int(ans)
}

func qpow(a int64, n int64, mod int64) int64 {
	a %= mod
	var ans int64 = 1
	for n > 0 {
		if n&1 == 1 {
			ans = ans * a % mod
		}
		a = a * a % mod
		n >>= 1
	}
	return ans
}
