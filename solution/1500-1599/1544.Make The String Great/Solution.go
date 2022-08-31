func makeGood(s string) string {
	stk := []rune{}
	for _, c := range s {
		if len(stk) == 0 || abs(int(stk[len(stk)-1]-c)) != 32 {
			stk = append(stk, c)
		} else {
			stk = stk[:len(stk)-1]
		}
	}
	return string(stk)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}