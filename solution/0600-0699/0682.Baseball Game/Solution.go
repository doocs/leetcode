func calPoints(operations []string) (ans int) {
	var stk []int
	for _, op := range operations {
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
	for _, x := range stk {
		ans += x
	}
	return
}