func reverseBits(n uint32) (ans uint32) {
	for i := 0; i < 32; i++ {
		ans |= (n & 1) << (31 - i)
		n >>= 1
	}
	return
}