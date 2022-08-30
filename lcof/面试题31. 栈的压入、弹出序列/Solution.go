func validateStackSequences(pushed []int, popped []int) bool {
	stk := []int{}
	j := 0
	for _, v := range pushed {
		stk = append(stk, v)
		for len(stk) > 0 && stk[len(stk)-1] == popped[j] {
			stk = stk[:len(stk)-1]
			j++
		}
	}
	return j == len(pushed)
}