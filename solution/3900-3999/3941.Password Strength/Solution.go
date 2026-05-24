func passwordStrength(password string) (ans int) {
	st := map[rune]struct{}{}

	for _, ch := range password {
		st[ch] = struct{}{}
	}

	for ch := range st {
		switch {
		case unicode.IsLower(ch):
			ans += 1
		case unicode.IsUpper(ch):
			ans += 2
		case unicode.IsDigit(ch):
			ans += 3
		default:
			ans += 5
		}
	}

	return
}