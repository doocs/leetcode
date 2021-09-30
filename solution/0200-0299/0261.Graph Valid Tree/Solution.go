var p []int

func validTree(n int, edges [][]int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	for _, e := range edges {
		if find(e[0]) == find(e[1]) {
			return false
		}
		p[find(e[0])] = find(e[1])
		n--
	}
	return n == 1
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}