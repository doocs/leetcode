var p []int

func minimumCost(n int, connections [][]int) int {
	p = make([]int, n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	sort.Slice(connections, func(i, j int) bool {
		return connections[i][2] < connections[j][2]
	})
	res := 0
	for _, e := range connections {
		if union(e[0], e[1]) {
			res += e[2]
			n--
			if n == 1 {
				return res
			}
		}
	}
	return -1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func union(a, b int) bool {
	pa, pb := find(a-1), find(b-1)
	if pa == pb {
		return false
	}
	p[pa] = pb
	return true
}