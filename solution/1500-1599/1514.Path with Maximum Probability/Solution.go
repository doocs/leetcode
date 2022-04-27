func maxProbability(n int, edges [][]int, succProb []float64, start int, end int) float64 {
	g := make([][]pair, n)
	for i, e := range edges {
		a, b, s := e[0], e[1], succProb[i]
		g[a] = append(g[a], pair{b, s})
		g[b] = append(g[b], pair{a, s})
	}
	d := make([]float64, n)
	d[start] = 1
	vis := make([]bool, n)
	q := []int{start}
	vis[start] = true
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		vis[i] = false
		for _, ne := range g[i] {
			j, s := ne.idx, ne.s
			if d[j] < d[i]*s {
				d[j] = d[i] * s
				if !vis[j] {
					q = append(q, j)
					vis[j] = true
				}
			}
		}
	}
	return d[end]
}

type pair struct {
	idx int
	s   float64
}