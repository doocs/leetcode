func minTimeToReach(moveTime [][]int) int {
	n, m := len(moveTime), len(moveTime[0])
	dist := make([][]int, n)
	for i := range dist {
		dist[i] = make([]int, m)
		for j := range dist[i] {
			dist[i][j] = math.MaxInt32
		}
	}
	dist[0][0] = 0

	pq := &hp{}
	heap.Init(pq)
	heap.Push(pq, tuple{0, 0, 0})

	dirs := []int{-1, 0, 1, 0, -1}
	for {
		p := heap.Pop(pq).(tuple)
		d, i, j := p.dis, p.x, p.y

		if i == n-1 && j == m-1 {
			return d
		}
		if d > dist[i][j] {
			continue
		}

		for k := 0; k < 4; k++ {
			x, y := i+dirs[k], j+dirs[k+1]
			if x >= 0 && x < n && y >= 0 && y < m {
				t := max(moveTime[x][y], dist[i][j]) + 1
				if dist[x][y] > t {
					dist[x][y] = t
					heap.Push(pq, tuple{t, x, y})
				}
			}
		}
	}
}

type tuple struct{ dis, x, y int }
type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].dis < h[j].dis }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() (v any)      { a := *h; *h, v = a[:len(a)-1], a[len(a)-1]; return }
