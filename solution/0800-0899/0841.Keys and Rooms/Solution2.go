func canVisitAllRooms(rooms [][]int) bool {
	n := len(rooms)
	vis := make([]bool, n)
	cnt := 0
	q := []int{0}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if vis[i] {
			continue
		}
		vis[i] = true
		cnt++
		for _, j := range rooms[i] {
			q = append(q, j)
		}
	}
	return cnt == n
}
