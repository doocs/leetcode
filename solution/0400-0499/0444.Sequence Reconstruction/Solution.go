func sequenceReconstruction(nums []int, sequences [][]int) bool {
	n := len(nums)
	indeg := make([]int, n)
	g := make([][]int, n)
	for _, seq := range sequences {
		for i, b := range seq[1:] {
			a := seq[i] - 1
			b -= 1
			g[a] = append(g[a], b)
			indeg[b]++
		}
	}
	q := []int{}
	for i, x := range indeg {
		if x == 0 {
			q = append(q, i)
		}
	}
	for len(q) == 1 {
		i := q[0]
		q = q[1:]
		for _, j := range g[i] {
			indeg[j]--
			if indeg[j] == 0 {
				q = append(q, j)
			}
		}
	}
	return len(q) == 0
}