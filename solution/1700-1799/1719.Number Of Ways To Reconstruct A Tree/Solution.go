func checkWays(pairs [][]int) int {
	g := make([][]bool, 510)
	v := make([][]int, 510)
	for i := range g {
		g[i] = make([]bool, 510)
	}
	for _, p := range pairs {
		x, y := p[0], p[1]
		g[x][y] = true
		g[y][x] = true
		v[x] = append(v[x], y)
		v[y] = append(v[y], x)
	}
	var nodes []int
	for i := 1; i <= 500; i++ {
		if len(v[i]) > 0 {
			nodes = append(nodes, i)
			g[i][i] = true
		}
	}
	sort.Slice(nodes, func(i, j int) bool {
		return len(v[nodes[i]]) < len(v[nodes[j]])
	})
	equal := false
	root := 0
	for i, x := range nodes {
		j := i + 1
		for ; j < len(nodes) && !g[x][nodes[j]]; j++ {
		}
		if j < len(nodes) {
			y := nodes[j]
			if len(v[x]) == len(v[y]) {
				equal = true
			}
			for _, z := range v[x] {
				if !g[y][z] {
					return 0
				}
			}
		} else {
			root++
		}
	}
	if root > 1 {
		return 0
	}
	if equal {
		return 2
	}
	return 1
}