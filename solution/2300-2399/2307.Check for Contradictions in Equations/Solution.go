func checkContradictions(equations [][]string, values []float64) bool {
	d := make(map[string]int)
	n := 0

	for _, e := range equations {
		for _, s := range e {
			if _, ok := d[s]; !ok {
				d[s] = n
				n++
			}
		}
	}

	p := make([]int, n)
	for i := range p {
		p[i] = i
	}

	w := make([]float64, n)
	for i := range w {
		w[i] = 1.0
	}

	var find func(int) int
	find = func(x int) int {
		if p[x] != x {
			root := find(p[x])
			w[x] *= w[p[x]]
			p[x] = root
		}
		return p[x]
	}
	for i, e := range equations {
		a, b := d[e[0]], d[e[1]]
		v := values[i]

		pa, pb := find(a), find(b)
		if pa != pb {
			p[pb] = pa
			w[pb] = v * w[a] / w[b]
		} else if v*w[a]-w[b] >= 1e-5 || w[b]-v*w[a] >= 1e-5 {
			return true
		}
	}

	return false
}