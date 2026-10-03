func countGoodStrings(low int, high int, zero int, one int) int {
	const mod int = 1e9 + 7
	f := make([]int, high+1)
	for i := high; i >= 0; i-- {
		ans := 0
		if i >= low && i <= high {
			ans++
		}
		if i+zero <= high {
			ans += f[i+zero]
		}
		if i+one <= high {
			ans += f[i+one]
		}
		f[i] = ans % mod
	}
	return f[0]
}
