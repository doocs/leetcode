func minCost(n int, edges [][]int, k int) int {
	p := make([]int, n)
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

	if k == n {
		return 0
	}

	slices.SortFunc(edges, func(a, b []int) int {
		return a[2] - b[2]
	})

	cnt := n
	for _, e := range edges {
		u, v, w := e[0], e[1], e[2]
		pu, pv := find(u), find(v)
		if pu != pv {
			p[pu] = pv
			if cnt--; cnt <= k {
				return w
			}
		}
	}

	return 0
}