func totalCost(costs []int, k int, candidates int) (ans int64) {
	n := len(costs)
	if candidates*2 > n {
		sort.Ints(costs)
		for _, x := range costs[:k] {
			ans += int64(x)
		}
		return
	}
	pq := hp{}
	for i, x := range costs[:candidates] {
		heap.Push(&pq, pair{x, i})
		heap.Push(&pq, pair{costs[n-i-1], n - i - 1})
	}
	l, r := candidates, n-candidates-1
	for ; k > 0; k-- {
		p := heap.Pop(&pq).(pair)
		ans += int64(p.cost)
		if l > r {
			continue
		}
		if p.i < l {
			heap.Push(&pq, pair{costs[l], l})
			l++
		} else {
			heap.Push(&pq, pair{costs[r], r})
			r--
		}
	}
	return
}

type pair struct{ cost, i int }
type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	return h[i].cost < h[j].cost || (h[i].cost == h[j].cost && h[i].i < h[j].i)
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }