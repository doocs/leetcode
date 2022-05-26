func minAddToMakeValid(s string) int {
	var stk []rune
	for _, c := range s {
		if c == '(' {
			stk = append(stk, c)
		} else {
			if len(stk) > 0 && stk[len(stk)-1] == '(' {
				stk = stk[:len(stk)-1]
			} else {
				stk = append(stk, c)
			}
		}
	}
	return len(stk)
}