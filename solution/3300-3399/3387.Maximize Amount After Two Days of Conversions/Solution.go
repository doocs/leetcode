type Pair struct {
	Key   string
	Value float64
}

func maxAmount(initialCurrency string, pairs1 [][]string, rates1 []float64, pairs2 [][]string, rates2 []float64) (ans float64) {
	d1 := build(pairs1, rates1, initialCurrency)
	d2 := build(pairs2, rates2, initialCurrency)
	for currency, rate := range d2 {
		if val, found := d1[currency]; found {
			ans = max(ans, val/rate)
		}
	}
	return
}

func build(pairs [][]string, rates []float64, init string) map[string]float64 {
	g := make(map[string][]Pair)
	d := make(map[string]float64)

	for i := 0; i < len(pairs); i++ {
		a := pairs[i][0]
		b := pairs[i][1]
		r := rates[i]
		g[a] = append(g[a], Pair{Key: b, Value: r})
		g[b] = append(g[b], Pair{Key: a, Value: 1.0 / r})
	}

	dfs(g, d, init, 1.0)
	return d
}

func dfs(g map[string][]Pair, d map[string]float64, a string, v float64) {
	if _, found := d[a]; found {
		return
	}

	d[a] = v
	for _, pair := range g[a] {
		b := pair.Key
		r := pair.Value
		if _, found := d[b]; !found {
			dfs(g, d, b, v*r)
		}
	}
}