func validateStackSequences(pushed []int, popped []int) bool {
	stk := []int{}
	i := 0
	for _, x := range pushed {
		stk = append(stk, x)
		for len(stk) > 0 && stk[len(stk)-1] == popped[i] {
			stk = stk[:len(stk)-1]
			i++
		}
	}
	return i == len(popped)
}