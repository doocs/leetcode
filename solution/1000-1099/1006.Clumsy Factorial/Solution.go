func clumsy(n int) (ans int) {
	stk := []int{n}
	k := 0
	for x := n - 1; x > 0; x-- {
		switch k {
		case 0:
			stk[len(stk)-1] *= x
		case 1:
			stk[len(stk)-1] /= x
		case 2:
			stk = append(stk, x)
		case 3:
			stk = append(stk, -x)
		}
		k = (k + 1) % 4
	}
	for _, x := range stk {
		ans += x
	}
	return
}