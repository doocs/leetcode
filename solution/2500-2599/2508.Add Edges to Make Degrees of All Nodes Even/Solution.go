func isPossible(n int, edges [][]int) bool {
	g := make([]map[int]bool, n+1)
	for _, e := range edges {
		a, b := e[0], e[1]
		if g[a] == nil {
			g[a] = map[int]bool{}
		}
		if g[b] == nil {
			g[b] = map[int]bool{}
		}
		g[a][b], g[b][a] = true, true
	}
	vs := []int{}
	for i := 1; i <= n; i++ {
		if len(g[i])%2 == 1 {
			vs = append(vs, i)
		}
	}
	if len(vs) == 0 {
		return true
	}
	if len(vs) == 2 {
		a, b := vs[0], vs[1]
		if !g[a][b] {
			return true
		}
		for c := 1; c <= n; c++ {
			if a != c && b != c && !g[a][c] && !g[c][b] {
				return true
			}
		}
		return false
	}
	if len(vs) == 4 {
		a, b, c, d := vs[0], vs[1], vs[2], vs[3]
		if !g[a][b] && !g[c][d] {
			return true
		}
		if !g[a][c] && !g[b][d] {
			return true
		}
		if !g[a][d] && !g[b][c] {
			return true
		}
		return false
	}
	return false
}