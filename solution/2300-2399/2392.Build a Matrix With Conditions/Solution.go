func buildMatrix(k int, rowConditions [][]int, colConditions [][]int) [][]int {
	f := func(cond [][]int) []int {
		g := make([][]int, k+1)
		indeg := make([]int, k+1)
		for _, e := range cond {
			a, b := e[0], e[1]
			g[a] = append(g[a], b)
			indeg[b]++
		}
		q := []int{}
		for i, v := range indeg[1:] {
			if v == 0 {
				q = append(q, i+1)
			}
		}
		res := []int{}
		for len(q) > 0 {
			for n := len(q); n > 0; n-- {
				i := q[0]
				q = q[1:]
				res = append(res, i)
				for _, j := range g[i] {
					indeg[j]--
					if indeg[j] == 0 {
						q = append(q, j)
					}
				}
			}
		}
		if len(res) == k {
			return res
		}
		return []int{}
	}

	row := f(rowConditions)
	col := f(colConditions)
	if len(row) == 0 || len(col) == 0 {
		return [][]int{}
	}
	m := make([]int, k+1)
	for i, v := range col {
		m[v] = i
	}
	ans := make([][]int, k)
	for i := range ans {
		ans[i] = make([]int, k)
	}
	for i, v := range row {
		ans[i][m[v]] = v
	}
	return ans
}