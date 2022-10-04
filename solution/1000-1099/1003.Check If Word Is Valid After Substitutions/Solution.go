func isValid(s string) bool {
	if len(s)%3 > 0 {
		return false
	}
	stk := []rune{}
	for _, c := range s {
		n := len(stk)
		if c == 'c' && n > 1 && stk[n-2] == 'a' && stk[n-1] == 'b' {
			stk = stk[:n-2]
		} else {
			stk = append(stk, c)
		}
	}
	return len(stk) == 0
}