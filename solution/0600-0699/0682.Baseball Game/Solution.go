func calPoints(ops []string) int {
	var stk []int
	for _, op := range ops {
		n := len(stk)
		switch op {
		case "+":
			stk = append(stk, stk[n-1]+stk[n-2])
		case "D":
			stk = append(stk, stk[n-1]*2)
		case "C":
			stk = stk[:n-1]
		default:
			num, _ := strconv.Atoi(op)
			stk = append(stk, num)
		}
	}
	ans := 0
	for _, score := range stk {
		ans += score
	}
	return ans
}