func verifyPostorder(postorder []int) bool {
	mx := 1 << 30
	stk := []int{}
	for i := len(postorder) - 1; i >= 0; i-- {
		x := postorder[i]
		if x > mx {
			return false
		}
		for len(stk) > 0 && stk[len(stk)-1] > x {
			mx = stk[len(stk)-1]
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, x)
	}
	return true
}