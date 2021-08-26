var p []int

func findRedundantConnection(edges [][]int) []int {
	p = make([]int, 1010)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for _, e := range edges {
		if find(e[0]) == find(e[1]) {
			return e
		}
		p[find(e[0])] = find(e[1])
	}
	return nil
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}