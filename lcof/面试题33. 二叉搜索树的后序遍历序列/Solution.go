func verifyPostorder(postorder []int) bool {
	n := len(postorder)
	stk := [][2]int{{0, n - 1}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		l, r := cur[0], cur[1]
		if l >= r {
			continue
		}
		v := postorder[r]
		i := l
		for i < r && postorder[i] < v {
			i++
		}
		for j := i; j < r; j++ {
			if postorder[j] < v {
				return false
			}
		}
		stk = append(stk, [2]int{i, r - 1}, [2]int{l, i - 1})
	}
	return true
}
