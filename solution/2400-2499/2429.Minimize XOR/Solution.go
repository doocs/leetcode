func minimizeXor(num1 int, num2 int) int {
	cnt := bits.OnesCount(uint(num2))
	ans := 0
	for i := 30; i >= 0; i-- {
		if num1>>i&1 == 1 {
			ans |= 1 << i
			cnt--
			if cnt == 0 {
				return ans
			}
		}
	}
	for i := 0; i < 31; i++ {
		if num1>>i&1 == 0 {
			ans |= 1 << i
			cnt--
			if cnt == 0 {
				return ans
			}
		}
	}
	return 0
}