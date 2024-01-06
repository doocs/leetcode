func distanceToCycle(n int, edges [][]int) []int {
	g := make([]map[int]bool, n)
	for i := range g {
		g[i] = map[int]bool{}
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a][b] = true
		g[b][a] = true
	}
	q := []int{}
	for i := 0; i < n; i++ {
		if len(g[i]) == 1 {
			q = append(q, i)
		}
	}
	f := make([]int, n)
	seq := []int{}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		seq = append(seq, i)
		for j := range g[i] {
			delete(g[j], i)
			f[i] = j
			if len(g[j]) == 1 {
				q = append(q, j)
			}
		}
		g[i] = map[int]bool{}
	}
	ans := make([]int, n)
	for k := len(seq) - 1; k >= 0; k-- {
		i := seq[k]
		ans[i] = ans[f[i]] + 1
	}
	return ans
}