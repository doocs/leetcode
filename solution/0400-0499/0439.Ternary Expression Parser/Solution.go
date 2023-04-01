func parseTernary(expression string) string {
	stk := []byte{}
	cond := false
	for i := len(expression) - 1; i >= 0; i-- {
		c := expression[i]
		if c == ':' {
			continue
		}
		if c == '?' {
			cond = true
		} else {
			if cond {
				if c == 'T' {
					x := stk[len(stk)-1]
					stk = stk[:len(stk)-2]
					stk = append(stk, x)
				} else {
					stk = stk[:len(stk)-1]
				}
				cond = false
			} else {
				stk = append(stk, c)
			}
		}
	}
	return string(stk[0])
}