func bitwiseComplement(n int) (ans int) {
	if n == 0 {
		return 1
	}
	for i := 0; n != 0; n >>= 1 {
		ans |= (n&1 ^ 1) << i
		i++
	}
	return
}
