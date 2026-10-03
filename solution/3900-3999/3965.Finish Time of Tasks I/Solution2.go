func finishTime(n int, edges [][]int, baseTime []int) int64 {
	g := make([][]int, n)
	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}
	fin := make([]int64, n)
	stk := [][2]int{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, state := cur[0], cur[1]
		if state == 0 {
			if len(g[i]) == 0 {
				fin[i] = int64(baseTime[i])
			} else {
				stk = append(stk, [2]int{i, 1})
				for _, j := range g[i] {
					stk = append(stk, [2]int{j, 0})
				}
			}
		} else {
			var inf int64 = 1 << 62
			earliest, latest := inf, -inf
			for _, j := range g[i] {
				earliest = min(earliest, fin[j])
				latest = max(latest, fin[j])
			}
			ownDuration := (latest - earliest) + int64(baseTime[i])
			fin[i] = latest + ownDuration
		}
	}
	return fin[0]
}
