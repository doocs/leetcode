func goodBinaryStrings(minLength int, maxLength int, oneGroup int, zeroGroup int) (ans int) {
	const mod int = 1e9 + 7
	f := make([]int, maxLength+1)
	f[0] = 1
	for i := 1; i <= maxLength; i++ {
		if i-oneGroup >= 0 {
			f[i] += f[i-oneGroup]
		}
		if i-zeroGroup >= 0 {
			f[i] += f[i-zeroGroup]
		}
		f[i] %= mod
	}
	for _, v := range f[minLength:] {
		ans = (ans + v) % mod
	}
	return
}