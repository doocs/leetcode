func minimumEffortPath(heights [][]int) int {
	m, n := len(heights), len(heights[0])
	dist := make([][]int, m)
	for i := range dist {
		dist[i] = make([]int, n)
		for j := range dist[i] {
			dist[i][j] = 1 << 30
		}
	}
	dirs := [5]int{-1, 0, 1, 0, -1}
	dist[0][0] = 0
	pq := hp{}
	heap.Push(&pq, tuple{0, 0, 0})
	for pq.Len() > 0 {
		p := heap.Pop(&pq).(tuple)
		t, i, j := p.t, p.i, p.j
		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n {
				if d := max(t, abs(heights[x][y]-heights[i][j])); d < dist[x][y] {
					dist[x][y] = d
					heap.Push(&pq, tuple{d, x, y})
				}
			}
		}
	}
	return dist[m-1][n-1]
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

type tuple struct{ t, i, j int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].t < h[j].t }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }