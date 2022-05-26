var p []int
var w []float64

func calcEquation(equations [][]string, values []float64, queries [][]string) []float64 {
	n := len(equations)
	p = make([]int, (n<<1)+10)
	w = make([]float64, (n<<1)+10)
	for i := 0; i < (n<<1)+10; i++ {
		p[i] = i
		w[i] = 1.0
	}
	mp := make(map[string]int)
	idx := 1
	for i, e := range equations {
		a, b := e[0], e[1]
		if mp[a] == 0 {
			mp[a] = idx
			idx++
		}
		if mp[b] == 0 {
			mp[b] = idx
			idx++
		}
		pa, pb := find(mp[a]), find(mp[b])
		if pa == pb {
			continue
		}
		p[pa] = pb
		w[pa] = w[mp[b]] * values[i] / w[mp[a]]
	}
	var res []float64
	for _, q := range queries {
		c, d := q[0], q[1]
		if mp[c] == 0 || mp[d] == 0 {
			res = append(res, -1.0)
		} else {
			pa, pb := find(mp[c]), find(mp[d])
			if pa == pb {
				res = append(res, w[mp[c]]/w[mp[d]])
			} else {
				res = append(res, -1.0)
			}
		}
	}
	return res
}

func find(x int) int {
	if p[x] != x {
		origin := p[x]
		p[x] = find(p[x])
		w[x] *= w[origin]
	}
	return p[x]
}