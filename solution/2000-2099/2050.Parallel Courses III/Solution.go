func minimumTime(n int, relations [][]int, time []int) int {
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, e := range relations {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		indeg[b]++
	}
	f := make([]int, n)
	q := []int{}
	ans := 0
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
			f[i] = time[i]
			ans = max(ans, time[i])
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
			f[j] = max(f[j], f[i]+time[j])
			ans = max(ans, f[j])
		}
	}
	return ans
}