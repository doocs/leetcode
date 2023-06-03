func minCostToSupplyWater(n int, wells []int, pipes [][]int) (ans int) {
	for i, w := range wells {
		pipes = append(pipes, []int{0, i + 1, w})
	}
	sort.Slice(pipes, func(i, j int) bool { return pipes[i][2] < pipes[j][2] })
	p := make([]int, n+1)
	for i := range p {
		p[i] = i
	}
	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}

	for _, x := range pipes {
		pa, pb := find(x[0]), find(x[1])
		if pa == pb {
			continue
		}
		p[pa] = pb
		ans += x[2]
		n--
		if n == 0 {
			break
		}
	}
	return
}