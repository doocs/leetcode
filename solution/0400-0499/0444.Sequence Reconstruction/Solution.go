func sequenceReconstruction(nums []int, sequences [][]int) bool {
	n := len(nums)
	g := make([][]int, n)
	indeg := make([]int, n)
	for _, seq := range sequences {
		for i := 1; i < len(seq); i++ {
			a, b := seq[i-1]-1, seq[i]-1
			g[a] = append(g[a], b)
			indeg[b]++
		}
	}
	q := []int{}
	for i, v := range indeg {
		if v == 0 {
			q = append(q, i)
		}
	}
	for len(q) > 0 {
		if len(q) > 1 {
			return false
		}
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return true
}