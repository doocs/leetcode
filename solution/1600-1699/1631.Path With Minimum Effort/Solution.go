var p []int

func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	p = make([]int, m*n)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	var edges [][]int
	for i := 0; i < m; i++ {
		for j := 0; j < n; j++ {
			if i < m-1 {
				s := []int{abs(heights[i][j] - heights[i+1][j]), i*n + j, (i+1)*n + j}
				edges = append(edges, s)
			}
			if j < n-1 {
				s := []int{abs(heights[i][j] - heights[i][j+1]), i*n + j, i*n + j + 1}
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

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}