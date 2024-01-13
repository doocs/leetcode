func minCostConnectPoints(points [][]int) int {
	n := len(points)
	var g [][]int
	for i, p := range points {
		x1, y1 := p[0], p[1]
		for j := i + 1; j < n; j++ {
			x2, y2 := points[j][0], points[j][1]
			g = append(g, []int{abs(x1-x2) + abs(y1-y2), i, j})
		}
	}
	sort.Slice(g, func(i, j int) bool {
		return g[i][0] < g[j][0]
	})
	ans := 0
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
	for _, e := range g {
		cost, i, j := e[0], e[1], e[2]
		if find(i) == find(j) {
			continue
		}
		p[find(i)] = find(j)
		ans += cost
		n--
		if n == 1 {
			return ans
		}
	}
	return 0
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}