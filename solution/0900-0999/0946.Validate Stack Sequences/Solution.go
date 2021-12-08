func validateStackSequences(pushed []int, popped []int) bool {
	j, n := 0, len(popped)
	var stk []int
	for _, x := range pushed {
		stk = append(stk, x)
		for len(stk) > 0 && j < n && stk[len(stk)-1] == popped[j] {
			stk = stk[:len(stk)-1]
			j++
		}
	}
	return j == n
}