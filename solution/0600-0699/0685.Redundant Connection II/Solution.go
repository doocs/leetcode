func findRedundantDirectedConnection(edges [][]int) []int {
	n := len(edges)
	ind := make([]int, n)
	for _, e := range edges {
		ind[e[1]-1]++
	}
	dup := []int{}
	for i, e := range edges {
		if ind[e[1]-1] == 2 {
			dup = append(dup, i)
		}
	}
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
	if len(dup) > 0 {
		for i, e := range edges {
			if i == dup[1] {
				continue
			}
			pu, pv := find(e[0]-1), find(e[1]-1)
			if pu == pv {
				return edges[dup[0]]
			}
			p[pu] = pv
		}
		return edges[dup[1]]
	}
	for _, e := range edges {
		pu, pv := find(e[0]-1), find(e[1]-1)
		if pu == pv {
			return e
		}
		p[pu] = pv
	}
	return nil
}
