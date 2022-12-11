func maxPoints(grid [][]int, queries []int) []int {
	k := len(queries)
	qs := make([]pair, k)
	for i, v := range queries {
		qs[i] = pair{v, i}
	}
	sort.Slice(qs, func(i, j int) bool { return qs[i].v < qs[j].v })
	ans := make([]int, k)
	m, n := len(grid), len(grid[0])
	q := hp{}
	heap.Push(&q, tuple{grid[0][0], 0, 0})
	dirs := []int{-1, 0, 1, 0, -1}
	vis := map[int]bool{0: true}
	cnt := 0
	for _, e := range qs {
		v := e.v
		k = e.i
		for len(q) > 0 && q[0].v < v {
			p := heap.Pop(&q).(tuple)
			i, j := p.i, p.j
			cnt++
			for h := 0; h < 4; h++ {
				x, y := i+dirs[h], j+dirs[h+1]
				if x >= 0 && x < m && y >= 0 && y < n && !vis[x*n+y] {
					vis[x*n+y] = true
					heap.Push(&q, tuple{grid[x][y], x, y})
				}
			}
		}
		ans[k] = cnt
	}
	return ans
}

type pair struct{ v, i int }

type tuple struct{ v, i, j int }
type hp []tuple

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].v < h[j].v }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }