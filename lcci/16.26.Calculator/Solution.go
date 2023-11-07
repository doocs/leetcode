func calculate(s string) (ans int) {
	n := len(s)
	x := 0
	sign := '+'
	stk := []int{}
	for i := range s {
		if s[i] >= '0' && s[i] <= '9' {
			x = x*10 + int(s[i]-'0')
		}
		if i == n-1 || (s[i] != ' ' && (s[i] < '0' || s[i] > '9')) {
			switch sign {
			case '+':
				stk = append(stk, x)
			case '-':
				stk = append(stk, -x)
			case '*':
				stk[len(stk)-1] *= x
			case '/':
				stk[len(stk)-1] /= x
			}
			x = 0
			sign = rune(s[i])
		}
	}
	for _, x := range stk {
		ans += x
	}
	return
}