var p []int

func makeConnected(n int, connections [][]int) int {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	cnt := 0
	for _, e := range connections {
		if find(e[0]) == find(e[1]) {
			cnt++
		} else {
			p[find(e[0])] = find(e[1])
		}
	}
	total := 0
	for i := 0; i < n; i++ {
		if i == find(i) {
			total++
		}
	}
	if total-1 > cnt {
		return -1
	}
	return total - 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}