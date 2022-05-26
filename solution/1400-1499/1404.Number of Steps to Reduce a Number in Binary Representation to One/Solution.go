func numSteps(s string) int {
	ans := 0
	carry := false
	for i := len(s) - 1; i > 0; i-- {
		c := s[i]
		if carry {
			if c == '0' {
				c = '1'
				carry = false
			} else {
				c = '0'
			}
		}
		if c == '1' {
			ans++
			carry = true
		}
		ans++
	}
	if carry {
		ans++
	}
	return ans
}