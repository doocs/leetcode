var p map[string]string
var w map[string]float64

func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	p = make(map[string]string)
	w = make(map[string]float64)
	for _, e := range equations {
		p[e[0]] = e[0]
		p[e[1]] = e[1]
		w[e[0]] = 1.0
		w[e[1]] = 1.0
	}
	for i, e := range equations {
		a, b := e[0], e[1]
		pa, pb := find(a), find(b)
		if pa == pb {
			continue
		}
		p[pa] = pb
		w[pa] = w[b] * values[i] / w[a]
	}
	var res []float64
	for _, e := range queries {
		c, d := e[0], e[1]
		if p[c] == "" || p[d] == "" || find(c) != find(d) {
			res = append(res, -1.0)
		} else {
			res = append(res, w[c]/w[d])
		}
	}
	return res
}

func find(x string) string {
	if p[x] != x {
		origin := p[x]
		p[x] = find(p[x])
		w[x] *= w[origin]
	}
	return p[x]
}