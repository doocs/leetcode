func calculate(s string) int {
	q := []byte(s)
	var dfs func() int
	dfs = func() int {
		num, sign := 0, byte('+')
		var stk []int
		for len(q) > 0 {
			c := q[0]
			q = q[1:]
			if c >= '0' && c <= '9' {
				num = num*10 + int(c-'0')
			}
			if c == '(' {
				num = dfs()
			}
			if c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || len(q) == 0 {
				switch sign {
				case '+':
					stk = append(stk, num)
				case '-':
					stk = append(stk, -num)
				case '*':
					stk[len(stk)-1] *= num
				default:
					stk[len(stk)-1] /= num
				}
				num, sign = 0, c
			}
			if c == ')' {
				break
			}
		}
		ans := 0
		for _, x := range stk {
			ans += x
		}
		return ans
	}
	return dfs()
}
