func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	p := make([]int, m*n)
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
	edges := [][]int{}
	for i, row := range heights {
		for j, h := range row {
			if i < m-1 {
				s := []int{abs(h - heights[i+1][j]), i*n + j, (i+1)*n + j}
				edges = append(edges, s)
			}
			if j < n-1 {
				s := []int{abs(h - row[j+1]), i*n + j, i*n + j + 1}
				edges = append(edges, s)
			}
		}
	}
	sort.Slice(edges, func(i, j int) bool {
		return edges[i][0] < edges[j][0]
	})
	for _, e := range edges {
		i, j := e[1], e[2]
		p[find(i)] = find(j)
		if find(0) == find(m*n-1) {
			return e[0]
		}
	}
	return 0
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}