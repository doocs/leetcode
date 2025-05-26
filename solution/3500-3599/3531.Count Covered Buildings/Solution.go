func countCoveredBuildings(n int, buildings [][]int) (ans int) {
	g1 := make(map[int][]int)
	g2 := make(map[int][]int)

	for _, building := range buildings {
		x, y := building[0], building[1]
		g1[x] = append(g1[x], y)
		g2[y] = append(g2[y], x)
	}

	for _, list := range g1 {
		sort.Ints(list)
	}
	for _, list := range g2 {
		sort.Ints(list)
	}

	for _, building := range buildings {
		x, y := building[0], building[1]
		l1 := g1[x]
		l2 := g2[y]

		if l2[0] < x && x < l2[len(l2)-1] && l1[0] < y && y < l1[len(l1)-1] {
			ans++
		}
	}
	return
}
