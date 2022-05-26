func minimumCost(n int, connections [][]int) int {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	sort.Slice(connections, func(i, j int) bool {
		return connections[i][2] < connections[j][2]
	})
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	ans := 0
	for _, e := range connections {
		x, y, cost := e[0]-1, e[1]-1, e[2]
		if find(x) == find(y) {
			continue
		}
		p[find(x)] = find(y)
		ans += cost
		n--
		if n == 1 {
			return ans
		}
	}
	return -1
}