func strongPasswordCheckerII(password string) bool {
	if len(password) < 8 {
		return false
	}
	ans := 0
	for i, c := range password {
		if i > 0 && password[i] == password[i-1] {
			return false
		}
		if unicode.IsLower(c) {
			ans |= 1
		} else if unicode.IsUpper(c) {
			ans |= 2
		} else if unicode.IsDigit(c) {
			ans |= 4
		} else {
			ans |= 8
		}
	}
	return ans == 15
}