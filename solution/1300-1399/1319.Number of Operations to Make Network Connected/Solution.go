func makeConnected(n int, connections [][]int) int {
	p := make([]int, n)
	for i := range p {
		p[i] = i
	}
	cnt := 0
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	for _, e := range connections {
		a, b := e[0], e[1]
		if find(a) == find(b) {
			cnt++
		} else {
			p[find(a)] = find(b)
			n--
		}
	}
	if n-1 > cnt {
		return -1
	}
	return n - 1
}