func strongPasswordCheckerII(password string) bool {
	if len(password) < 8 {
		return false
	}
	mask := 0
	for i, c := range password {
		if i > 0 && byte(c) == password[i-1] {
			return false
		}
		if unicode.IsLower(c) {
			mask |= 1
		} else if unicode.IsUpper(c) {
			mask |= 2
		} else if unicode.IsDigit(c) {
			mask |= 4
		} else {
			mask |= 8
		}
	}
	return mask == 15
}