func findAllRecipes(recipes []string, ingredients [][]string, supplies []string) []string {
	g := map[string][]string{}
	indeg := map[string]int{}
	for i, a := range recipes {
		for _, b := range ingredients[i] {
			g[b] = append(g[b], a)
		}
		indeg[a] = len(ingredients[i])
	}
	q := []string{}
	for _, s := range supplies {
		q = append(q, s)
	}
	ans := []string{}
	for len(q) > 0 {
		for n := len(q); n > 0; n-- {
			i := q[0]
			q = q[1:]
			for _, j := range g[i] {
				indeg[j]--
				if indeg[j] == 0 {
					ans = append(ans, j)
					q = append(q, j)
				}
			}
		}
	}
	return ans
}