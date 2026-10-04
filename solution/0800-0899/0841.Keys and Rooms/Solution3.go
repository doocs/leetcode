func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make([]bool, n)
	stk := []int{0}
	for len(stk) > 0 {
		i := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		if vis[i] {
			continue
		}
		vis[i] = true
		for _, j := range rooms[i] {
			stk = append(stk, j)
		}
	}
	for _, v := range vis {
		if !v {
			return false
		}
	}
	return true
}
