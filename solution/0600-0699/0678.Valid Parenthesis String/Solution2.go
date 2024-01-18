func checkValidString(s string) bool {
	x := 0
	for _, c := range s {
		if c != ')' {
			x++
		} else if x > 0 {
			x--
		} else {
			return false
		}
	}
	x = 0
	for i := len(s) - 1; i >= 0; i-- {
		if s[i] != '(' {
			x++
		} else if x > 0 {
			x--
		} else {
			return false
		}
	}
	return true
}