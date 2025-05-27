func resultingString(s string) string {
	isContiguous := func(a, b rune) bool {
		x := abs(int(a - b))
		return x == 1 || x == 25
	}
	stk := []rune{}
	for _, c := range s {
		if len(stk) > 0 && isContiguous(stk[len(stk)-1], c) {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, c)
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