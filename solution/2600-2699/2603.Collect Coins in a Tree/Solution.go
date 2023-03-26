func collectTheCoins(coins []int, edges [][]int) int {
	n := len(coins)
	g := make([]map[int]bool, n)
	for i := range g {
		g[i] = map[int]bool{}
	}
	for _, e := range edges {
		a, b := e[0], e[1]
		g[a][b] = true
		g[b][a] = true
	}
	q := []int{}
	for i, c := range coins {
		if c == 0 && len(g[i]) == 1 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		for j := range g[i] {
			delete(g[j], i)
			if coins[j] == 0 && len(g[j]) == 1 {
				q = append(q, j)
			}
		}
		g[i] = map[int]bool{}
	}
	for k := 0; k < 2; k++ {
		q := []int{}
		for i := range coins {
			if len(g[i]) == 1 {
				q = append(q, i)
			}
		}
		for _, i := range q {
			for j := range g[i] {
				delete(g[j], i)
			}
			g[i] = map[int]bool{}
		}
	}
	ans := 0
	for _, e := range edges {
		a, b := e[0], e[1]
		if len(g[a]) > 0 && len(g[b]) > 0 {
			ans += 2
		}
	}
	return ans
}