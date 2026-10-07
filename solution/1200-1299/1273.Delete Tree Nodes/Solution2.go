func deleteTreeNodes(nodes int, parent []int, value []int) int {
	g := make([][]int, nodes)
	for i := 1; i < nodes; i++ {
		g[parent[i]] = append(g[parent[i]], i)
	}
	sum := make([]int, nodes)
	cnt := make([]int, nodes)
	stk := [][2]int{{0, 0}}
	for len(stk) > 0 {
		cur := stk[len(stk)-1]
		stk = stk[:len(stk)-1]
		i, state := cur[0], cur[1]
		if state == 0 {
			stk = append(stk, [2]int{i, 1})
			for _, j := range g[i] {
				stk = append(stk, [2]int{j, 0})
			}
		} else {
			s, m := value[i], 1
			for _, j := range g[i] {
				s += sum[j]
				m += cnt[j]
			}
			if s == 0 {
				m = 0
			}
			sum[i] = s
			cnt[i] = m
		}
	}
	return cnt[0]
}
