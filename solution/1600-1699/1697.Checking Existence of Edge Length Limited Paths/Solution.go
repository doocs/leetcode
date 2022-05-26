func distanceLimitedPathsExist(n int, edgeList [][]int, queries [][]int) []bool {
	p := make([]int, n)
	for i := 0; i < n; i++ {
		p[i] = i
	}
	var find func(x int) int
	find = func(x int) int {
		if p[x] != x {
			p[x] = find(p[x])
		}
		return p[x]
	}
	sort.Slice(edgeList, func(i, j int) bool {
		return edgeList[i][2] < edgeList[j][2]
	})
	m := len(queries)
	indexes := make([]int, m)
	for i := 0; i < m; i++ {
		indexes[i] = i
	}
	sort.Slice(indexes, func(i, j int) bool {
		return queries[indexes[i]][2] < queries[indexes[j]][2]
	})
	ans := make([]bool, m)
	i := 0
	for _, j := range indexes {
		pj, qj, limit := queries[j][0], queries[j][1], queries[j][2]
		for i < len(edgeList) && edgeList[i][2] < limit {
			u, v := edgeList[i][0], edgeList[i][1]
			p[find(u)] = find(v)
			i++
		}
		ans[j] = find(pj) == find(qj)
	}
	return ans
}