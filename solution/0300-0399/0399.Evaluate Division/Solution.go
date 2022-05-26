func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	p := make(map[string]string)
	w := make(map[string]float64)
	for _, e := range equations {
		a, b := e[0], e[1]
		p[a], p[b] = a, b
		w[a], w[b] = 1.0, 1.0
	}

	var find func(x string) string
	find = func(x string) string {
		if p[x] != x {
			origin := p[x]
			p[x] = find(p[x])
			w[x] *= w[origin]
		}
		return p[x]
	}

	for i, v := range values {
		a, b := equations[i][0], equations[i][1]
		pa, pb := find(a), find(b)
		if pa == pb {
			continue
		}
		p[pa] = pb
		w[pa] = w[b] * v / w[a]
	}
	var ans []float64
	for _, e := range queries {
		c, d := e[0], e[1]
		if p[c] == "" || p[d] == "" || find(c) != find(d) {
			ans = append(ans, -1.0)
		} else {
			ans = append(ans, w[c]/w[d])
		}
	}
	return ans
}