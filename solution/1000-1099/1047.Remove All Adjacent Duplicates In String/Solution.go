func removeDuplicates(s string) string {
	stk := []rune{}
	for _, c := range s {
		if len(stk) > 0 && stk[len(stk)-1] == c {
			stk = stk[:len(stk)-1]
		} else {
			stk = append(stk, c)
		}
	}
	return string(stk)
}