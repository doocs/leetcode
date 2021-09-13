var p []int

func possibleBipartition(n int, dislikes [][]int) bool {
	p = make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	mp := make(map[int][]int)
	for _, e := range dislikes {
		mp[e[0]-1] = append(mp[e[0]-1], e[1]-1)
		mp[e[1]-1] = append(mp[e[1]-1], e[0]-1)
	}
	for i := 0; i < n; i++ {
		dis := mp[i]
		for _, j := range dis {
			if find(i) == find(j) {
				return false
			}
			p[find(j)] = find(dis[0])
		}
	}
	return true
}

func find(x int) int {
	if p[x] != x {
		p[x] = find(p[x])
	}
	return p[x]
}