func minMutation(startGene string, endGene string, bank []string) int {
	type pair struct {
		s     string
		depth int
	}
	q := []pair{pair{startGene, 0}}
	vis := map[string]bool{startGene: true}
	for len(q) > 0 {
		p := q[0]
		q = q[1:]
		if p.s == endGene {
			return p.depth
		}
		for _, next := range bank {
			diff := 0
			for i := 0; i < len(startGene); i++ {
				if p.s[i] != next[i] {
					diff++
				}
			}
			if diff == 1 && !vis[next] {
				vis[next] = true
				q = append(q, pair{next, p.depth + 1})
			}
		}
	}
	return -1
}