func bitwiseComplement(n int) int {
	if n == 0 {
		return 1
	}
	ans := 0
	find := false
	for i := 30; i >= 0; i-- {
		b := n & (1 << i)
		if !find && b == 0 {
			continue
		}
		find = true
		if b == 0 {
			ans |= (1 << i)
		}
	}
	return ans
}