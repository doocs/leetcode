var p []int

func minCostToSupplyWater(n int, wells []int, pipes [][]int) int {
	p = make([]int, n+1)
	for i := 0; i < len(p); i++ {
		p[i] = i
	}
	for i, w := range wells {
		pipes = append(pipes, []int{0, i + 1, w})
	}
	sort.Slice(pipes, func(i, j int) bool {
		return pipes[i][2] < pipes[j][2]
	})
	res := 0
	for _, e := range pipes {
		if find(e[0]) == find(e[1]) {
			continue
		}
		p[find(e[0])] = find(e[1])
		res += e[2]
		n--
		if n == 0 {
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