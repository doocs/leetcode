var p []int

func minCostConnectPoints(points [][]int) int {
	n := len(points)
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	var edges [][]int
	for i := 0; i < n; i++ {
		x1, y1 := points[i][0], points[i][1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			edges = append(edges, []int{abs(x1-x2) + abs(y1-y2), i, j})
		}
	}
	sort.Slice(edges, func(i, j int) bool {
		return edges[i][0] < edges[j][0]
	})
	res := 0
	for _, e := range edges {
		if find(e[1]) == find(e[2]) {
			continue
		}
		p[find(e[1])] = find(e[2])
		n--
		res += e[0]
		if n == 1 {
			break
		}
	}
	return res
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