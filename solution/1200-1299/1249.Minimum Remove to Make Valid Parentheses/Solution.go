func minRemoveToMakeValid(s string) string {
	stk := []byte{}
	x := 0
	for i := range s {
		c := s[i]
		if c == ')' && x == 0 {
			continue
		}
		if c == '(' {
			x++
		} else if c == ')' {
			x--
		}
		stk = append(stk, c)
	}
	ans := []byte{}
	x = 0
	for i := len(stk) - 1; i >= 0; i-- {
		c := stk[i]
		if c == '(' && x == 0 {
			continue
		}
		if c == ')' {
			x++
		} else if c == '(' {
			x--
		}
		ans = append(ans, c)
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}