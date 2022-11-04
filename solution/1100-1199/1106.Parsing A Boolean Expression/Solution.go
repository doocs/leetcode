func parseBoolExpr(expression string) bool {
	stk := []rune{}
	for _, c := range expression {
		if c != '(' && c != ')' && c != ',' {
			stk = append(stk, c)
		} else if c == ')' {
			var t, f int
			for stk[len(stk)-1] == 't' || stk[len(stk)-1] == 'f' {
				if stk[len(stk)-1] == 't' {
					t++
				} else {
					f++
				}
				stk = stk[:len(stk)-1]
			}
			op := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			c = 'f'
			switch op {
			case '!':
				if f > 0 {
					c = 't'
				}
			case '&':
				if f == 0 {
					c = 't'
				}
			case '|':
				if t > 0 {
					c = 't'
				}
			}
			stk = append(stk, c)
		}
	}
	return stk[0] == 't'
}