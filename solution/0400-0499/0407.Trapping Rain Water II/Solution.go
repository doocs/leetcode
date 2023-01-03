func trapRainWater(heightMap [][]int) (ans int) {
	m, n := len(heightMap), len(heightMap[0])
	pq := hp{}
	vis := make([][]bool, m)
	for i, row := range heightMap {
		vis[i] = make([]bool, n)
		for j, v := range row {
			if i == 0 || i == m-1 || j == 0 || j == n-1 {
				heap.Push(&pq, tuple{v, i, j})
				vis[i][j] = true
			}
		}
	}
	dirs := []int{-1, 0, 1, 0, -1}
	for len(pq) > 0 {
		p := heap.Pop(&pq).(tuple)
		for k := 0; k < 4; k++ {
			x, y := p.i+dirs[k], p.j+dirs[k+1]
			if x >= 0 && x < m && y >= 0 && y < n && !vis[x][y] {
				ans += max(0, p.v-heightMap[x][y])
				vis[x][y] = true
				heap.Push(&pq, tuple{max(p.v, heightMap[x][y]), x, y})
			}
		}
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

type tuple struct{ v, i, j int }
type hp []tuple

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }