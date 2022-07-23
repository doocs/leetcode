func minimumSemesters(n int, relations [][]int) int {
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, r := range relations {
		a, b := r[0]-1, r[1]-1
		g[a] = append(g[a], b)
		indeg[b]++
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	ans := 0
	for len(q) > 0 {
		ans++
		for k := len(q); k > 0; k-- {
			i := q[0]
			q = q[1:]
			n--
			for _, j := range g[i] {
				indeg[j]--
				if indeg[j] == 0 {
					q = append(q, j)
				}
			}
		}
	}
	if n == 0 {
		return ans
	}
	return -1
}