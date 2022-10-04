func isValid(s string) bool {
	stk := []rune{}
	for _, c := range s {
		if c == '(' || c == '{' || c == '[' {
			stk = append(stk, c)
		} else if len(stk) == 0 || !match(stk[len(stk)-1], c) {
			return false
		} else {
			stk = stk[:len(stk)-1]
		}
	}
	return len(stk) == 0
}

func match(l, r rune) bool {
	return (l == '(' && r == ')') || (l == '[' && r == ']') || (l == '{' && r == '}')
}