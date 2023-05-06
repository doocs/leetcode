func calculate(s string) (ans int) {
	stk := []int{}
	sign := 1
	n := len(s)
	for i := 0; i < n; i++ {
		switch s[i] {
		case ' ':
		case '+':
			sign = 1
		case '-':
			sign = -1
		case '(':
			stk = append(stk, ans)
			stk = append(stk, sign)
			ans, sign = 0, 1
		case ')':
			ans *= stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			ans += stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		default:
			x := 0
			j := i
			for ; j < n && '0' <= s[j] && s[j] <= '9'; j++ {
				x = x*10 + int(s[j]-'0')
			}
			ans += sign * x
			i = j - 1
		}
	}
	return
}