func possibleBipartition(n int, dislikes [][]int) bool {
	g := make([][]int, n)
	for _, e := range dislikes {
		a, b := e[0]-1, e[1]-1
		g[a] = append(g[a], b)
		g[b] = append(g[b], a)
	}
	color := make([]int, n)
	for start := 0; start < n; start++ {
		if color[start] != 0 {
			continue
		}
		color[start] = 1
		stk := []int{start}
		for len(stk) > 0 {
			i := stk[len(stk)-1]
			stk = stk[:len(stk)-1]
			for _, j := range g[i] {
				if color[j] == color[i] {
					return false
				}
				if color[j] == 0 {
					color[j] = 3 - color[i]
					stk = append(stk, j)
				}
			}
		}
	}
	return true
}
