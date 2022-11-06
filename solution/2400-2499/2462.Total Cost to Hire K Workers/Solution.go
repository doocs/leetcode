func totalCost(costs []int, k int, candidates int) int64 {
	q := hp{}
	n := len(costs)
	i, j := candidates-1, n-candidates
	for h := 0; h < candidates; h++ {
		heap.Push(&q, pair{costs[h], h})
	}
	for h := n - candidates; h < n; h++ {
		if h > i {
			heap.Push(&q, pair{costs[h], h})
		}
	}
	ans := 0
	for k > 0 {
		p := heap.Pop(&q).(pair)
		c, x := p.c, p.x
		ans += c
		if x <= i {
			i++
			if i < j {
				heap.Push(&q, pair{costs[i], i})
			}
		}
		if x >= j {
			j--
			if i < j {
				heap.Push(&q, pair{costs[j], j})
			}
		}
		k--
	}
	return int64(ans)
}

type pair struct{ c, x int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].c < h[j].c || h[i].c == h[j].c && h[i].x < h[j].x }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }