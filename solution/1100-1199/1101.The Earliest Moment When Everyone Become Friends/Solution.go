func earliestAcq(logs [][]int, n int) int {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	sort.Slice(logs, func(i, j int) bool {
		return logs[i][0] < logs[j][0]
	})
	for _, log := range logs {
		t, a, b := log[0], log[1], log[2]
		if find(a) == find(b) {
			continue
		}
		p[find(a)] = find(b)
		n--
		if n == 1 {
			return t
		}
	}
	return -1
}