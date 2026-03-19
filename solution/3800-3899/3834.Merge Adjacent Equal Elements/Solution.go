func mergeAdjacent(nums []int) []int64 {
	stk := []int64{}
	for _, x := range nums {
		stk = append(stk, int64(x))
		for len(stk) > 1 && stk[len(stk)-1] == stk[len(stk)-2] {
			a := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			b := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			stk = append(stk, a+b)
		}
	}
	return stk
}
