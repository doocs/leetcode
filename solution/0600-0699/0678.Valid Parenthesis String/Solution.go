func checkValidString(s string) bool {
	n := len(s)
	left, asterisk := 0, 0
	for i := 0; i < n; i++ {
		if s[i] == '(' {
			left++
		} else if s[i] == ')' {
			if left > 0 {
				left--
			} else if asterisk > 0 {
				asterisk--
			} else {
				return false
			}
		} else {
			asterisk++
		}
	}
	asterisk = 0
	right := 0
	for i := n - 1; i >= 0; i-- {
		if s[i] == ')' {
			right++
		} else if s[i] == '(' {
			if right > 0 {
				right--
			} else if asterisk > 0 {
				asterisk--
			} else {
				return false
			}
		} else {
			asterisk++
		}
	}
	return true
}
