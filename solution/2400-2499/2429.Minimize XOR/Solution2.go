func minimizeXor(num1 int, num2 int) int {
	cnt1 := bits.OnesCount(uint(num1))
	cnt2 := bits.OnesCount(uint(num2))
	for ; cnt1 > cnt2; cnt1-- {
		num1 &= (num1 - 1)
	}
	for ; cnt1 < cnt2; cnt1++ {
		num1 |= (num1 + 1)
	}
	return num1
}