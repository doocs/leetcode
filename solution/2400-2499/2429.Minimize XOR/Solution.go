func minimizeXor(num1 int, num2 int) int {
	cnt := bits.OnesCount(uint(num2))
	x := 0
	for i := 30; i >= 0 && cnt > 0; i-- {
		if num1>>i&1 == 1 {
			x |= 1 << i
			cnt--
		}
	}
	for i := 0; cnt > 0; i++ {
		if num1>>i&1 == 0 {
			x |= 1 << i
			cnt--
		}
	}
	return x
}