func possibleBipartition(n int, dislikes [][]int) bool {
	p := make([]int, n)
	dis := make([][]int, n)
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
	for _, d := range dislikes {
		a, b := d[0]-1, d[1]-1
		dis[a] = append(dis[a], b)
		dis[b] = append(dis[b], a)
	}
	for i := 0; i < n; i++ {
		for _, j := range dis[i] {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(dis[i][0])
		}
	}
	return true
}