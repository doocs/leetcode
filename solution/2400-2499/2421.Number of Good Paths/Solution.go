func numberOfGoodPaths(vals []int, edges [][]int) int {
	n := len(vals)
	p := make([]int, n)
	size := map[int]map[int]int{}
	type pair struct{ v, i int }
	arr := make([]pair, n)
	for i, v := range vals {
		p[i] = i
		if size[i] == nil {
			size[i] = map[int]int{}
		}
		size[i][v] = 1
		arr[i] = pair{v, i}
	}

	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}

	sort.Slice(arr, func(i, j int) bool { return arr[i].v < arr[j].v })
	g := make([][]int, n)
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	ans := n
	for _, e := range arr {
		v, a := e.v, e.i
		for _, b := range g[a] {
			if vals[b] > v {
				continue
			}
			pa, pb := find(a), find(b)
			if pa != pb {
				ans += size[pb][v] * size[pa][v]
				p[pa] = pb
				size[pb][v] += size[pa][v]
			}
		}
	}
	return ans
}