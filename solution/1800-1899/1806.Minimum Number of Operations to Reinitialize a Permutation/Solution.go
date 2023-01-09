func reinitializePermutation(n int) (ans int) {
	for i := 1; ; {
		ans++
		if i < (n >> 1) {
			i <<= 1
		} else {
			i = (i-(n>>1))<<1 | 1
		}
		if i == 1 {
			return ans
		}
	}
}