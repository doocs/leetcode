const inf int64 = 1 << 60

type tuple struct {
	d    int64
	i, j int
	k    int
}

type hp []tuple

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].d < h[j].d }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *hp) Push(x any) {
	*h = append(*h, x.(tuple))
}

func (h *hp) Pop() any {
	a := *h
	v := a[len(a)-1]
	*h = a[:len(a)-1]
	return v
}

func minCost(m int, n int, penalty [][]int) int64 {
	dist := make([][][]int64, m)
	for i := range dist {
		dist[i] = make([][]int64, n)
		for j := range dist[i] {
			dist[i][j] = []int64{inf, inf}
		}
	}
	dist[0][0][1] = 1

	pq := hp{{1, 0, 0, 1}}
	heap.Init(&pq)

	dirs := [][2]int{{-1, 0}, {0, 1}, {0, -1}, {1, 0}}

	for pq.Len() > 0 {
		cur := heap.Pop(&pq).(tuple)
		d, i, j, k := cur.d, cur.i, cur.j, cur.k

		if i == m-1 && j == n-1 {
			return d
		}
		if d > dist[i][j][k] {
			continue
		}

		p := penalty[i][j]

		nd := d + int64(p)
		if nd < dist[i][j][k^1] {
			dist[i][j][k^1] = nd
			heap.Push(&pq, tuple{nd, i, j, k ^ 1})
		}

		for idx, dir := range dirs {
			x, y := i+dir[0], j+dir[1]
			if 0 <= x && x < m && 0 <= y && y < n {
				nd = d + int64((x+1)*(y+1)+((idx&1)^k)*p)
				if nd < dist[x][y][k^1] {
					dist[x][y][k^1] = nd
					heap.Push(&pq, tuple{nd, x, y, k ^ 1})
				}
			}
		}
	}

	return -1
}
